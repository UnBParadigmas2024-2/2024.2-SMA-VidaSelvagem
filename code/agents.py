from mesa import Agent

class WildlifeAgent(Agent):
    def __init__(self, unique_id, model, agent_type):
        super().__init__(unique_id, model)
        self.energy = 10  # Energia inicial
        self.agent_type = agent_type  # Tipo do agente (herbívoro, carívoro, planta)

    def move(self):
        """Move o agente para uma célula vizinha."""
        possible_moves = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )
        new_position = self.random.choice(possible_moves)
        self.model.grid.move_agent(self, new_position)

class Herbivore(WildlifeAgent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model, agent_type="Herbívoro")
        self.reproduction_energy_threshold = 10  # Energia mínima para reproduzir

    def move(self):
        """Move o herbívoro para uma célula com planta, se possível."""
        possible_moves = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )
        plant_positions = [
            pos for pos in possible_moves
            if any(isinstance(agent, Plant) for agent in self.model.grid.get_cell_list_contents([pos]))
        ]
        if plant_positions:
            new_position = self.random.choice(plant_positions)
        else:
            new_position = self.random.choice(possible_moves)
        self.model.grid.move_agent(self, new_position)

    def step(self):
        """Define o comportamento do herbívoro a cada etapa."""
        self.move()
        self.energy -= 1  # Perde energia ao se mover

        # Comer planta (se houver)
        cell_content = self.model.grid.get_cell_list_contents([self.pos])
        for obj in cell_content:
            if isinstance(obj, Plant):
                self.energy += 5  # Recupera energia ao comer
                self.model.grid.remove_agent(obj)  # Remove a planta
                print(f"Herbívoro {self.unique_id} comeu uma planta na posição {self.pos}. Energia atual: {self.energy}")
                break

        # Reproduzir
        if self.energy >= self.reproduction_energy_threshold:
            new_herbivore = Herbivore(self.model.next_id(), self.model)
            self.model.grid.place_agent(new_herbivore, self.pos)
            self.model.schedule.add(new_herbivore)
            self.energy -= 5  # Perde energia ao reproduzir
            print(f"Herbívoro {self.unique_id} se reproduziu na posição {self.pos}. Energia atual: {self.energy}")

        # Morre se a energia acabar
        if self.energy <= 0:
            print(f"Herbívoro {self.unique_id} morreu na posição {self.pos}.")
            self.model.grid.remove_agent(self)
            self.model.schedule.remove(self)

class Carnivore(WildlifeAgent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model, agent_type="Carnívoro")
        self.reproduction_energy_threshold = 15  # Energia mínima para reproduzir

    def step(self):
        """Define o comportamento do carívoro a cada etapa."""
        self.move()
        self.energy -= 1  # Perde energia ao se mover

        # Caçar herbívoros (se houver)
        cell_content = self.model.grid.get_cell_list_contents([self.pos])
        for obj in cell_content:
            if isinstance(obj, Herbivore):
                self.energy += 10  # Recupera energia ao caçar
                self.model.grid.remove_agent(obj)  # Remove o herbívoro
                self.model.schedule.remove(obj)
                print(f"Carnívoro {self.unique_id} caçou um herbívoro na posição {self.pos}. Energia atual: {self.energy}")
                break

        # Reproduzir
        if self.energy >= self.reproduction_energy_threshold:
            new_carnivore = Carnivore(self.model.next_id(), self.model)
            self.model.grid.place_agent(new_carnivore, self.pos)
            self.model.schedule.add(new_carnivore)
            self.energy -= 10  # Perde energia ao reproduzir
            print(f"Carnívoro {self.unique_id} se reproduziu na posição {self.pos}. Energia atual: {self.energy}")

        # Morre se a energia acabar
        if self.energy <= 0:
            print(f"Carnívoro {self.unique_id} morreu na posição {self.pos}.")
            self.model.grid.remove_agent(self)
            self.model.schedule.remove(self)

class Plant(Agent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.agent_type = "Planta"

    def step(self):
        """Plantas podem regenerar, com chance baseada no ambiente."""
        if self.pos is None:  # Verificar se a posição está definida
            return
        
        if self.random.random() < self.model.plant_regrowth_chance:
            empty_cells = [
                cell for cell in self.model.grid.get_neighborhood(self.pos, moore=True, include_center=False)
                if self.model.grid.is_cell_empty(cell)
            ]
            if empty_cells:
                new_position = self.random.choice(empty_cells)
                new_plant = Plant(self.model.next_id(), self.model)
                self.model.grid.place_agent(new_plant, new_position)
                self.model.schedule.add(new_plant)
                print(f"Nova planta cresceu na posição {new_position}.")

from agents import Herbivore, Carnivore, Plant

def move_agent(agent):
    """Move um agente para uma célula vizinha."""
    possible_moves = agent.model.grid.get_neighborhood(
        agent.pos, moore=True, include_center=False
    )
    new_position = agent.random.choice(possible_moves)
    agent.model.grid.move_agent(agent, new_position)

def herbivore_step(herbivore):
    """Define o comportamento de um herbívoro a cada etapa."""
    move_agent(herbivore)
    herbivore.energy -= 1  # Perde energia ao se mover

    # Comer planta
    cell_content = herbivore.model.grid.get_cell_list_contents([herbivore.pos])
    for obj in cell_content:
        if isinstance(obj, Plant):
            herbivore.energy += 5
            herbivore.model.grid.remove_agent(obj)
            print(f"Herbívoro {herbivore.unique_id} comeu uma planta na posição {herbivore.pos}. Energia atual: {herbivore.energy}")
            break

    # Reproduzir
    if herbivore.energy >= herbivore.reproduction_energy_threshold:
        new_herbivore = Herbivore(herbivore.model.next_id(), herbivore.model)
        herbivore.model.grid.place_agent(new_herbivore, herbivore.pos)
        herbivore.model.schedule.add(new_herbivore)
        herbivore.energy -= 5
        print(f"Herbívoro {herbivore.unique_id} se reproduziu na posição {herbivore.pos}. Energia atual: {herbivore.energy}")

    # Morre se a energia acabar
    if herbivore.energy <= 0:
        print(f"Herbívoro {herbivore.unique_id} morreu na posição {herbivore.pos}.")
        herbivore.model.grid.remove_agent(herbivore)
        herbivore.model.schedule.remove(herbivore)

def carnivore_step(carnivore):
    """Define o comportamento de um carnívoro a cada etapa."""
    move_agent(carnivore)
    carnivore.energy -= 1  # Perde energia ao se mover

    # Caçar herbívoros
    cell_content = carnivore.model.grid.get_cell_list_contents([carnivore.pos])
    for obj in cell_content:
        if isinstance(obj, Herbivore):
            carnivore.energy += 10
            carnivore.model.grid.remove_agent(obj)
            carnivore.model.schedule.remove(obj)
            print(f"Carnívoro {carnivore.unique_id} caçou um herbívoro na posição {carnivore.pos}. Energia atual: {carnivore.energy}")
            break

    # Reproduzir
    if carnivore.energy >= carnivore.reproduction_energy_threshold:
        new_carnivore = Carnivore(carnivore.model.next_id(), carnivore.model)
        carnivore.model.grid.place_agent(new_carnivore, carnivore.pos)
        carnivore.model.schedule.add(new_carnivore)
        carnivore.energy -= 10
        print(f"Carnívoro {carnivore.unique_id} se reproduziu na posição {carnivore.pos}. Energia atual: {carnivore.energy}")

    # Morre se a energia acabar
    if carnivore.energy <= 0:
        print(f"Carnívoro {carnivore.unique_id} morreu na posição {carnivore.pos}.")
        carnivore.model.grid.remove_agent(carnivore)
        carnivore.model.schedule.remove(carnivore)

def plant_step(plant):
    """Define o comportamento de uma planta a cada etapa."""
    if plant.pos is None:
        return

    if plant.random.random() < plant.model.plant_regrowth_chance:
        empty_cells = [
            cell for cell in plant.model.grid.get_neighborhood(plant.pos, moore=True, include_center=False)
            if plant.model.grid.is_cell_empty(cell)
        ]
        if empty_cells:
            new_position = plant.random.choice(empty_cells)
            new_plant = Plant(plant.model.next_id(), plant.model)
            plant.model.grid.place_agent(new_plant, new_position)
            plant.model.schedule.add(new_plant)
            print(f"Nova planta cresceu na posição {new_position}.")

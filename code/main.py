from mesa import Model
from mesa.space import MultiGrid
from mesa.time import RandomActivation
from agents import Herbivore, Carnivore, Plant

class WildlifeModel(Model):
    def __init__(self, width=10, height=10, initial_plants=5, plant_regrowth_chance=0.1):
        super().__init__()
        self.grid = MultiGrid(width, height, torus=True)
        self.schedule = RandomActivation(self)
        self.plant_regrowth_chance = plant_regrowth_chance  # Chance de regeneração das plantas

        # Criar e adicionar plantas iniciais
        for _ in range(initial_plants):
            plant = Plant(self.next_id(), self)
            self.grid.place_agent(plant, (self.random.randrange(width), self.random.randrange(height)))
            self.schedule.add(plant)

        # Criar herbívoros e carnívoros iniciais
        herbivore = Herbivore(self.next_id(), self)
        carnivore = Carnivore(self.next_id(), self)

        # Colocar os agentes no grid
        self.grid.place_agent(herbivore, (self.random.randrange(width), self.random.randrange(height)))
        self.grid.place_agent(carnivore, (self.random.randrange(width), self.random.randrange(height)))

        # Adicionar os agentes à agenda
        self.schedule.add(herbivore)
        self.schedule.add(carnivore)

    def step(self):
        """Executa um passo do modelo."""
        self.schedule.step()

if __name__ == "__main__":
    model = WildlifeModel(width=10, height=10, initial_plants=10, plant_regrowth_chance=0.2)

    print("Estado inicial dos agentes:")
    for agent in model.schedule.agents:
        print(f"Agente {agent.unique_id} ({agent.agent_type}) na posição {agent.pos} com energia {getattr(agent, 'energy', 'N/A')}")

    for step in range(10):  # Executar 10 passos
        print(f"\nPasso {step + 1}")
        model.step()

        for agent in model.schedule.agents:
            print(f"Agente {agent.unique_id} ({agent.agent_type}) na posição {agent.pos} com energia {getattr(agent, 'energy', 'N/A')}")

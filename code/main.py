from mesa import Model
from mesa.space import MultiGrid
from mesa.time import RandomActivation
from agents import Herbivore, Carnivore, Plant

class TestModel(Model):
    def __init__(self):
        super().__init__()
        self.grid = MultiGrid(width=10, height=10, torus=True)
        self.schedule = RandomActivation(self)

        # Criar e adicionar plantas
        for i in range(5):  # Adiciona 5 plantas aleatoriamente
            plant = Plant(self.next_id(), self)
            self.grid.place_agent(plant, (self.random.randrange(10), self.random.randrange(10)))
            self.schedule.add(plant)

        # Criar herbívoros e carnívoros
        herbivore = Herbivore(self.next_id(), self)
        carnivore = Carnivore(self.next_id(), self)

        # Colocar os agentes no grid
        self.grid.place_agent(herbivore, (5, 5))
        self.grid.place_agent(carnivore, (7, 7))

        # Adicionar os agentes à agenda
        self.schedule.add(herbivore)
        self.schedule.add(carnivore)

if __name__ == "__main__":
    model = TestModel()

    print("Estado inicial dos agentes:")
    for agent in model.schedule.agents:
        print(f"Agente {agent.unique_id} ({agent.agent_type}) na posição {agent.pos} com energia {getattr(agent, 'energy', 'N/A')}")

    for step in range(10):  # Executar 10 passos
        print(f"\nPasso {step + 1}")
        model.schedule.step()

        for agent in model.schedule.agents:
            print(f"Agente {agent.unique_id} ({agent.agent_type}) na posição {agent.pos} com energia {getattr(agent, 'energy', 'N/A')}")

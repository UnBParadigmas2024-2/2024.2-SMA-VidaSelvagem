from mesa import Agent

class WildlifeAgent(Agent):
    def __init__(self, unique_id, model, agent_type):
        super().__init__(unique_id, model)
        self.energy = 10  # Energia inicial
        self.agent_type = agent_type  # Tipo do agente (herbívoro, carnívoro, planta)

class Herbivore(WildlifeAgent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model, agent_type="Herbívoro")
        self.reproduction_energy_threshold = 10  # Energia mínima para reproduzir

class Carnivore(WildlifeAgent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model, agent_type="Carnívoro")
        self.reproduction_energy_threshold = 15  # Energia mínima para reproduzir

class Plant(Agent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.agent_type = "Planta"

package sma.simulador;

import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import javax.swing.*;

import static sma.simulador.MyInitialAgent.container;

public class Casa extends JPanel {

    //private Agent serVivo;
    private String nomeAgent;

    public Casa(){}

    public AgentController getSerVivo() throws ControllerException {
        return container.getAgent(nomeAgent);
    }

    public String getNomeAgent() {
        return nomeAgent;
    }

    public void setNomeAgent(String nomeAgent) {
        this.nomeAgent = nomeAgent;
    }
}

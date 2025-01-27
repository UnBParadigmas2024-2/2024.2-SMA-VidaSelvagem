package sma.simulador.agente;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import sma.simulador.Constantes;
import sma.simulador.MyInitialAgent;
import sma.simulador.TipoSer;
import sma.simulador.abstracao.Animal;
import sma.simulador.mensagem.Coordenadas;


public class Carnivoro extends Animal {


    public Carnivoro(){
        this.tipoSerVivo = TipoSer.Carnivoro;
        this.energia = 15;
        this.energiaMinimaParaReproducao = Constantes.ENERGIA_INICIAL_CARNIVORO;
    }

    @Override
    public void setup(){
        System.out.println("Carnivoro iniciado");

        Object[] args = getArguments();
        //setNome((String) args[0]);
        this.setX((int) args[1]);
        this.setY((int) args[2]);

        addBehaviour(new TickerBehaviour(this, 500) { // 1 segundos
            @Override
            protected void onTick() {
                try {
                    andar();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        // Espera pela resposta
        // Verifica se há algum ser vivo onde ele chegou
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage resposta = receive();
                if (resposta != null) {
                    try {
                        Coordenadas dados = (Coordenadas) resposta.getContentObject();

                        if(dados.getNomeAgente().contains(Constantes.PREFIXO_HERBIVORO)){
                            System.out.println(getLocalName() + " COME " + dados.getNomeAgente());
                            comer(dados);
                        }

                    } catch (UnreadableException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    block();
                }
            }
        });
    }

    @Override
    protected void comer(Coordenadas dados) {

        try {
            ACLMessage msgMatar = new ACLMessage(ACLMessage.REQUEST);
            msgMatar.addReceiver(getAID(MyInitialAgent.nomeAgente));
            msgMatar.setContentObject(dados);
            send(msgMatar);

            ContainerController controller = getContainerController();
            AgentController agentController = controller.getAgent(dados.getNomeAgente());
            agentController.kill();

            setEnergia(15);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

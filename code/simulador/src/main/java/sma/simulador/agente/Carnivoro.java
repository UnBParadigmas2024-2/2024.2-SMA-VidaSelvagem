package sma.simulador.agente;

import jade.core.behaviours.TickerBehaviour;
import sma.simulador.TipoSer;
import sma.simulador.abstracao.Animal;

;import java.io.IOException;

public class Carnivoro extends Animal {


    public Carnivoro(){
        this.tipoSerVivo = TipoSer.Carnivoro;
        this.energia = 10;
        this.energiaMinimaParaReproducao = 15;
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
    }

//    protected void andar() throws IOException {
//        //mandar mensagem para mapa para andar
//        // mapa vai alterar local do animal e vai deixar cheiro caso seja herbivoro
//
//        int modx = (int) (Math.random() * 3) - 1;
//        int mody = (int) (Math.random() * 3) - 1;
//
//        int novoX = (getX() + modx) % Constantes.DIMENSAO;
//        int novoY = (getY() + mody) % Constantes.DIMENSAO;
//
//        if(novoX < 0 || novoY < 0){
//            novoX = getX();
//            novoY = getY();
//        }
//
//        try {
//            MensagemMovimento dado = new MensagemMovimento(getLocalName(), getX(), getY(), novoX, novoY);
//
//            ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
//            mensagem.addReceiver(getAID(MyInitialAgent.nomeAgente));
//            mensagem.setContentObject(dado);
//            send(mensagem);
//
//            setX(novoX);
//            setY(novoY);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

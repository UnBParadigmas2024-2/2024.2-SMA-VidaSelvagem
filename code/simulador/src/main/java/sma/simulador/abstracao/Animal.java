package sma.simulador.abstracao;

import jade.lang.acl.ACLMessage;
import sma.simulador.Constantes;
import sma.simulador.MensagemMovimento;
import sma.simulador.MyInitialAgent;

import java.io.IOException;

public abstract class Animal extends SerVivo {

    protected int energiaMinimaParaReproducao;

    public int getEnergiaMinimaParaReproducao() {
        return energiaMinimaParaReproducao;
    }

    public void setEnergiaMinimaParaReproducao(int energiaMinimaParaReproducao) {
        this.energiaMinimaParaReproducao = energiaMinimaParaReproducao;
    }

    protected void andar() throws IOException {
        //mandar mensagem para mapa para andar
        // mapa vai alterar local do animal e vai deixar cheiro caso seja herbivoro

        int modx = (int) (Math.random() * 3) - 1;
        int mody = (int) (Math.random() * 3) - 1;

        int novoX = (getX() + modx) % Constantes.DIMENSAO;
        int novoY = (getY() + mody) % Constantes.DIMENSAO;

        if(novoX < 0 || novoY < 0){
            novoX = getX();
            novoY = getY();
        }

        try {
            MensagemMovimento dado = new MensagemMovimento(getLocalName(), getX(), getY(), novoX, novoY);

            ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
            mensagem.addReceiver(getAID(MyInitialAgent.nomeAgente));
            mensagem.setContentObject(dado);
            send(mensagem);

            setX(novoX);
            setY(novoY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
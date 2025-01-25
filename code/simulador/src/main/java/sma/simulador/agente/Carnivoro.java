package sma.simulador.agente;

import jade.core.behaviours.TickerBehaviour;
import sma.simulador.TipoSer;
import sma.simulador.abstracao.Animal;

;import java.io.IOException;

public class Carnivoro extends Animal {


    public Carnivoro(){
        this.tipoSerVivo = TipoSer.Carnivoro;
        this.energia = 15;
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

    @Override
    protected void comer() {

    }


}

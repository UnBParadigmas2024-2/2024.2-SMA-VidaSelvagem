package sma.simulador.agente;

import jade.core.behaviours.TickerBehaviour;
import sma.simulador.TipoSer;
import sma.simulador.abstracao.Animal;

public class Herbivoro extends Animal {

    public Herbivoro(){
        this.tipoSerVivo = TipoSer.Herbivoro;
        this.energia = 10;
        this.energiaMinimaParaReproducao = 10;
    }

    @Override
    public void setup(){

        Object[] args = getArguments();
        //setNome((String) args[0]);
        this.setX((int) args[1]);
        this.setY((int) args[2]);

        System.out.println("Herbívoro iniciado " + this.getX() + " " + this.getY());

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
}

package sma.simulador.agente;

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
        System.out.println("Carnivoro iniciado");
    }
}

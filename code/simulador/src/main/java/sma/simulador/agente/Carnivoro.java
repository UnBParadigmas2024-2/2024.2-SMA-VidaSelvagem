package sma.simulador.agente;

import sma.simulador.TipoSer;
import sma.simulador.abstracao.Animal;

;

public class Carnivoro extends Animal {


    public Carnivoro(){
        this.tipoSerVivo = TipoSer.Carnivoro;
        this.energia = 10;
        this.energiaMinimaParaReproducao = 15;
    }

    @Override
    public void setup(){
        System.out.println("Carnivoro iniciado");
    }
}

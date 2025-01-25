package sma.simulador.agente;

import sma.simulador.TipoSer;
import sma.simulador.abstracao.SerVivo;

public class Planta extends SerVivo {

    public Planta(){
        this.tipoSerVivo = TipoSer.Planta;
        this.energia = 10;
    }

    @Override
    public void setup(){
        System.out.println("Planta iniciada com energia de " + getEnergia());
    }

}
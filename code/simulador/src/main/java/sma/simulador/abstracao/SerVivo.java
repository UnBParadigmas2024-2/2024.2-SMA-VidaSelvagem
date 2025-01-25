package sma.simulador.abstracao;

import jade.core.Agent;
import sma.simulador.TipoSer;

public abstract class SerVivo extends Agent {

    protected int energia;
    protected TipoSer tipoSerVivo;

    public int getEnergia() {
        return energia;
    }

    public TipoSer getTipoSerVivo() {
        return tipoSerVivo;
    }
}

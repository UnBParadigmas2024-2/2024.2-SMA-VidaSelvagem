package sma.simulador.abstracao;

import jade.core.Agent;
import sma.simulador.TipoSer;

public abstract class SerVivo extends Agent {

    //private String nome;
    private int x,y;
    protected int energia;
    protected TipoSer tipoSerVivo;

//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia){
        this.energia = energia;
    }

    public TipoSer getTipoSerVivo() {
        return tipoSerVivo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

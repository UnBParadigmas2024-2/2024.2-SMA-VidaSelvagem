package sma.simulador.mensagem;

import jade.util.leap.Serializable;

public class Coordenadas implements Serializable {
    private String nomeAgente;
    private int x;
    private int y;

    public Coordenadas(String nomeAgente, int x, int y) {
        this.nomeAgente = nomeAgente;
        this.x = x;
        this.y = y;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
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

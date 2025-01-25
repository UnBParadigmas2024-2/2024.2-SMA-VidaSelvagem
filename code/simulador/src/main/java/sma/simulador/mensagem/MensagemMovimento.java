package sma.simulador.mensagem;

import jade.util.leap.Serializable;

public class MensagemMovimento implements Serializable {

    private String nomeAgenteOrigem;
    private int xAntigo;
    private int yAntigo;
    private int xNovo;
    private int yNovo;

    public MensagemMovimento(String nomeAgenteOrigem, int x, int y, int xNovo, int yNovo) {
        this.nomeAgenteOrigem = nomeAgenteOrigem;
        this.xAntigo = x;
        this.yAntigo = y;
        this.xNovo = xNovo;
        this.yNovo = yNovo;
    }

    public String getNomeAgenteOrigem() {
        return nomeAgenteOrigem;
    }

    public void setNomeAgenteOrigem(String nomeAgenteOrigem) {
        this.nomeAgenteOrigem = nomeAgenteOrigem;
    }

    public int getxAntigo() {
        return xAntigo;
    }

    public void setxAntigo(int xAntigo) {
        this.xAntigo = xAntigo;
    }

    public int getyAntigo() {
        return yAntigo;
    }

    public void setyAntigo(int yAntigo) {
        this.yAntigo = yAntigo;
    }

    public int getxNovo() {
        return xNovo;
    }

    public void setxNovo(int xNovo) {
        this.xNovo = xNovo;
    }

    public int getyNovo() {
        return yNovo;
    }

    public void setyNovo(int yNovo) {
        this.yNovo = yNovo;
    }
}

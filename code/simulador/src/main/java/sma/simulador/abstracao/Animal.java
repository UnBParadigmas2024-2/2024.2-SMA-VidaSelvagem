package sma.simulador.abstracao;

public abstract class Animal extends SerVivo {

    protected int energiaMinimaParaReproducao;

    public int getEnergiaMinimaParaReproducao() {
        return energiaMinimaParaReproducao;
    }

    public void setEnergiaMinimaParaReproducao(int energiaMinimaParaReproducao) {
        this.energiaMinimaParaReproducao = energiaMinimaParaReproducao;
    }
}
package sma.simulador;

import jade.core.*;

import javax.swing.*;
import java.awt.*;

public class MyInitialAgent extends Agent {

    private JFrame frame;
    private static JPanel[][] casas = new JPanel[20][20]; // Matriz para armazenar as casas do tabuleiro


    @Override
    protected void setup(){

        criarTabuleiro();

        frame.setVisible(true);

        for(int i=0; i<5; i++){
            try {
                casas[i][i].setBackground(Color.black);

                if(i != 0)
                    casas[i-1][i-1].setBackground(Color.yellow);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void criarTabuleiro(){

        frame = new JFrame("Simulador Vida Selvagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar programa ao fechar janela
        frame.setSize(600, 600); // Define o tamanho da janela


        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(20, 20));


        // 400 porque é 20X20
        for (int linha = 0; linha < 20; linha++) {
            for(int coluna = 0; coluna < 20; coluna++){
                JPanel casa = new JPanel();
                casa.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona borda para destacar as casas

                casas[linha][coluna] = casa;

                tabuleiro.add(casa);
            }

        }

        frame.add(tabuleiro);
    }
}
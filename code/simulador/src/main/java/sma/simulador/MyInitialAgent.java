package sma.simulador;

import jade.core.*;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import sma.simulador.agente.Planta;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyInitialAgent extends Agent {

    private JFrame frame;
    public static ContainerController container = null;

    private static Casa[][] casas = new Casa[Constantes.dimensao][Constantes.dimensao]; // Matriz para armazenar as casas do tabuleiro


    @Override
    public void setup(){

        criarTabuleiro();

        inicializarSeres();

        frame.setVisible(true);

//        for(int i=0; i<5; i++){
//            try {
//                casas[i][i].setBackground(Color.black);
//
//                if(i != 0)
//                    casas[i-1][i-1].setBackground(Color.yellow);
//
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }





    }

    private void inicializarSeres() {

        int numeroPlantas = 5;

        // Obtém o container onde este agente está rodando
        container = getContainerController();

        Random random = new Random();

        try{
            for(int i=0; i<numeroPlantas; i++){
                String nome = "Planta " + i;
                AgentController plantaController = container.createNewAgent(nome, Planta.class.getName(), null);

                int x = random.nextInt(50);
                int y = random.nextInt(50);

                while (true){
                    if(casas[x][y].getNomeAgent() != null){
                        x = random.nextInt(50);
                        y = random.nextInt(50);
                    }
                    else {
                        casas[x][y].setNomeAgent(nome);
                        casas[x][y].setBackground(Color.green);
                        break;
                    }
                }

                plantaController.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void criarTabuleiro(){

        frame = new JFrame("Simulador Vida Selvagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar programa ao fechar janela
        frame.setSize(600, 600); // Define o tamanho da janela


        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(Constantes.dimensao, Constantes.dimensao));


        // 400 porque é 20X20
        for (int linha = 0; linha < Constantes.dimensao; linha++) {
            for(int coluna = 0; coluna < Constantes.dimensao; coluna++){

                Casa casa = new Casa();
                casa.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona borda para destacar as casas

                casas[linha][coluna] = casa;

                tabuleiro.add(casa);
            }

        }

        frame.add(tabuleiro);
    }
}
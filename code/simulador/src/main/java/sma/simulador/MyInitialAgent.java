package sma.simulador;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import sma.simulador.agente.Carnivoro;
import sma.simulador.agente.Herbivoro;
import sma.simulador.agente.Planta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import sma.simulador.Coordenadas;

public class MyInitialAgent extends Agent {

    private JFrame frame;
    public static ContainerController container = null;
    private static Casa[][] casas = new Casa[Constantes.DIMENSAO][Constantes.DIMENSAO]; // Matriz para armazenar as casas do tabuleiro
    public static String nomeAgente = "MyInitialAgent";
    public List<Coordenadas> coordenadaCheiroList = new ArrayList<>();



    @Override
    public void setup(){

        criarTabuleiro();
        inicializarSeres();

        frame.setVisible(true);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensagem = receive();
                if (mensagem != null) {
                    try {
                        MensagemMovimento dados = (MensagemMovimento) mensagem.getContentObject();

                        moverAgente(dados);
                    } catch (UnreadableException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    block();
                }
            }
        });
    }

    private void moverAgente(MensagemMovimento dados){

        String nome = dados.getNomeAgenteOrigem();
        int xAntigo = dados.getxAntigo();
        int yAntigo = dados.getyAntigo();
        int xNovo = dados.getxNovo();
        int yNovo = dados.getyNovo();

        boolean herbivoro = nome.contains("Herbivoro");

        if(coordenadaCheiroList.size() > 25 && herbivoro)
            removeUltimoCheiro(nome);

        casas[xAntigo][yAntigo].setNomeAgent(null);
        casas[xNovo][yNovo].setNomeAgent(nome);

        if(herbivoro){
            //Adicionar rastro de cheiro
            coordenadaCheiroList.add(new Coordenadas(nome, xAntigo, yAntigo));

            casas[xNovo][yNovo].setBackground(Color.ORANGE);
            casas[xAntigo][yAntigo].setBackground(Color.lightGray);

            return;
        }

        casas[xAntigo][yAntigo].setBackground(Color.white);
        casas[xNovo][yNovo].setBackground(Color.red);

    }

    private void removeUltimoCheiro(String nomeAgente){
        Optional<Coordenadas> coordenadas = coordenadaCheiroList.stream().filter(f -> f.nomeAgente.equals(nomeAgente)).findFirst();

        if(coordenadas.isPresent()){
            var temp = coordenadas.get();
            coordenadaCheiroList.remove(coordenadas.get());
            casas[temp.x][temp.y].setBackground(Color.white);
        }

    }

    private void inicializarSeres() {

        // Obtém o container onde este agente está rodando
        container = getContainerController();

        Random random = new Random();

        try{
            for(int i=0; i<Constantes.NUMERO_INICIAL_PLANTAS; i++){
                String nome = "Planta " + i;
                AgentController plantaController = container.createNewAgent(nome, Planta.class.getName(), null);

                int x = random.nextInt(Constantes.DIMENSAO);
                int y = random.nextInt(Constantes.DIMENSAO);

                while (true){
                    if(casas[x][y].getNomeAgent() != null){
                        x = random.nextInt(Constantes.DIMENSAO);
                        y = random.nextInt(Constantes.DIMENSAO);
                    }
                    else {
                        casas[x][y].setNomeAgent(nome);
                        casas[x][y].setBackground(Color.green);
                        break;
                    }
                }

                plantaController.start();
            }

            for(int i=0; i<Constantes.NUMERO_INICIAL_HERBIVOROS; i++){
                String nome = "Herbivoro " + i;

                int x = random.nextInt(Constantes.DIMENSAO);
                int y = random.nextInt(Constantes.DIMENSAO);

                while (true){
                    if(casas[x][y].getNomeAgent() != null){
                        x = random.nextInt(Constantes.DIMENSAO);
                        y = random.nextInt(Constantes.DIMENSAO);
                    }
                    else {
                        casas[x][y].setNomeAgent(nome);
                        casas[x][y].setBackground(Color.ORANGE);
                        break;
                    }
                }
                AgentController herbivoroController = container.createNewAgent(nome, Herbivoro.class.getName(), new Object[]{nome, x, y});
                herbivoroController.start();
            }

            for(int i=0; i<Constantes.NUMERO_INICIAL_CARNIVOROS; i++){
                String nome = "Carnivoro " + i;

                int x = random.nextInt(Constantes.DIMENSAO);
                int y = random.nextInt(Constantes.DIMENSAO);

                while (true){
                    if(casas[x][y].getNomeAgent() != null){
                        x = random.nextInt(Constantes.DIMENSAO);
                        y = random.nextInt(Constantes.DIMENSAO);
                    }
                    else {
                        casas[x][y].setNomeAgent(nome);
                        casas[x][y].setBackground(Color.RED);
                        break;
                    }
                }
                AgentController carnivoroController = container.createNewAgent(nome, Carnivoro.class.getName(), new Object[]{nome, x, y});
                carnivoroController.start();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void criarTabuleiro(){

        frame = new JFrame("Simulador Vida Selvagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar programa ao fechar janela
        frame.setSize(700, 700); // Define o tamanho da janela


        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(Constantes.DIMENSAO, Constantes.DIMENSAO));


        // 400 porque é 20X20
        for (int linha = 0; linha < Constantes.DIMENSAO; linha++) {
            for(int coluna = 0; coluna < Constantes.DIMENSAO; coluna++){

                Casa casa = new Casa();
                casa.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona borda para destacar as casas

                casas[linha][coluna] = casa;

                tabuleiro.add(casa);
            }

        }

        frame.add(tabuleiro);
    }
}
# Requisitos de sistema

- JAVA 17 (JDK)
- IDE (Eclipse ou Intellij)

# Configurações para executar

- Baixar arquivos e importar `.jar` do JADE como bibliotecas externas  
    - jadeExamples.jar  
    - jade.jar
    - commons-codec-1.3.jar

- Definir como classe main `jade.Boot`
- Adicionar argumentos para rodar:
    - `-gui -localhost 127.0.0.1 -local-port 8082 jade.Boot;customAgent:sma.simulador.MyInitialAgent`


# Premissas

- Cada ser vivo é um agente.
- O objetivo de todo animal é se reproduzi, mas para isso precisa de ter energia.
- Carnivoros -> Herbívoros -> Plantas. '->' (Se alimenta de)
# Passo a Passo para Instalação do Projeto

## 1. Instalação do Java

1. Baixe o JDK (Java Development Kit) diretamente do site oficial da Oracle ou de uma distribuição open source, como o OpenJDK.
2. É necessário possuir o JDK 1.4 ou mais recente.
3. Instale o JDK seguindo as instruções fornecidas no instalador.
4. Verifique a instalação:
   - Abra o terminal ou prompt de comando.
   - Execute o comando `java -version` para verificar a versão instalada.
   - Execute o comando `javac -version` para verificar se o compilador está funcionando corretamente.
5. Configure a variável de ambiente `JAVA_HOME` apontando para o diretório raiz do JDK.
6. Baixe o arquivo `JadeAll.zip` no site oficial do Jade. Segue o link: 
   [JADE Official](http://jade.tilab.com).
7. Descompacte o arquivo `JadeAll.zip` e todos os arquivos zipados dentro deste, criando um diretório chamado `jade`. Caso você esteja no Windows, copie este diretório para `C:\` ou algum diretório de preferência.
8. Configure o `CLASSPATH`:
   - Vá em **Control Panel\System and Security\System**.
   - Clique em **Advanced system settings**.
   - Acesse **Environment Variables**.
   - Em **System Variables**, localize ou crie as seguintes variáveis:
     - `CLASSPATH` com o valor:
       ```
       C:\jade\lib\jade.jar;C:\jade\lib\jadeExamples.jar;C:\jade\lib\commons-codec\commons-codec-1.3.jar
       ```
     - `JAVA_HOME` com o valor:
       ```
       C:\Program Files\Java\jdk1.7.0_10
       ```
   - Em **User Variables**, localize ou crie a variável `PATH` com o valor:
     ```
     %JAVA_HOME%\bin;
     ```
     
## 2. Instalação do JADE (Windows)

1. Baixe o JADE (Java Agent DEvelopment Framework) do site oficial do projeto [jade](https\://jade.tilab.com/dl.php?file=JADE-all-4.6.0.zip) 
2. Extraia o arquivo zip/compactado em um diretório de sua preferência, como `C:\jade`.
3. Adicione os arquivos `.jar` do JADE ao **Classpath** do sistema:
   - No menu iniciar, procure por **Variáveis de Ambiente** e abra a configuração de **Editar variáveis de ambiente do sistema**.
   - Em **Variáveis do sistema**, localize a variável `CLASSPATH`.
     - Se ela não existir, clique em **Novo** e crie-a.
     - Adicione o caminho dos arquivos `.jar` do JADE, como `C:\jade\lib\jade.jar`.
4. Verifique a configuração:
   - Abra o prompt de comando.
   - Execute o comando `java -cp C:\jade\lib\jade.jar jade.Boot` para verificar se o JADE está funcionando corretamente.

## 3. Configuração do IntelliJ

### 3.1 Definir a biblioteca do projeto

1. No menu principal, acesse **File | Project Structure** ou use o atalho `Ctrl + Alt + Shift + S`.
2. Em **Project Settings**, selecione a opção **Libraries**.
3. Clique no botão **Add** (botão com um "+") e selecione:
   - **Java**: para adicionar os arquivos `.jar` do JADE localizados no diretório de instalação.
4. Confirme e salve as alterações clicando em **OK**.

### 3.2 Alterar o Runner do IntelliJ

1. Clique no ícone de **play** (seta verde) ao lado da declaração da classe principal do seu programa e selecione **Modify Run Configuration**.
2. Configure a classe principal (main class) para `jade.Boot`.
3. Adicione os seguintes argumentos no campo **Program arguments**:
   ```
   -gui -localhost 127.0.0.1 -local-port 8082 jade.Boot;MyInitialAgent:sma.simulador.MyInitialAgen
   ```
4. Ajuste outras opções, como o **JRE** (Java Runtime Environment) ou o **classpath** do projeto, se necessário.
5. Salve as alterações e execute o programa novamente.

Com esses passos, seu ambiente estará configurado para trabalhar com Java e JADE no IntelliJ IDEA.



# Premissas

- Cada ser vivo é um agente.
- O objetivo de todo animal é se reproduzir, mas, para isso, precisa ter energia.
- Carnívoros -> Herbívoros -> Plantas. ('->' significa "se alimenta de")

# Passo a Passo para Instalação do Projeto

## Passo 1: Instalar o Python

### Linux

No Linux, o Python geralmente já está instalado. Para verificar a versão, abra o terminal e execute:

```bash
python --version
```

Se o Python não estiver instalado, use o seguinte comando para instalar:
```bash
sudo apt-get update
sudo apt-get install python3
```

## Passo 2: Criar um Ambiente Virtual 

Criar um ambiente virtual garante que você isole as dependências do seu projeto. No terminal ou prompt de comando, digite:


### Criar o ambiente virtual:

```bash
python -m venv venv
```

### Ative o ambiente virtual:

```bash
source venv/bin/activate
```

## Passo 3: Instalar o MESA 
```bash
pip install mesa
```

### Passo 4: Verificar a Instalação
Para garantir que o MESA foi instalado corretamente, inicie o Python:

```bash
python
```
E execute o seguinte comando:
```bash
import mesa
print(mesa.__version__)
```
Se não houver erros e a versão for exibida, a instalação foi concluída com sucesso.
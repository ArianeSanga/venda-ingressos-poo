# Sobre o Projeto

Sistema de venda de ingressos com interface gráfica Swing, desenvolvido em Java.
O sistema permite comprar ingressos por setor, gera relatórios e persiste os dados via serialização.

## Tecnologias

- Java 17
- IntelliJ IDEA
- Git / GitHub

## Estrutura de Pacotes

| Pacote | Responsabilidade |
|--------|-----------------|
| venda_ingresso.main | Ponto de entrada da aplicação |
| venda_ingresso.entities | Entidades do domínio (Ingresso) |
| venda_ingresso.enums | Enumerações do sistema (SetorEnum) |
| venda_ingresso.exceptions | Exceções personalizadas |
| venda_ingresso.services | Regras de negócio e persistência |
| venda_ingresso.ui | Interface gráfica Swing |

## Como Executar

1. Clone o repositório:
```
git clone https://github.com/ArianeSanga/venda-ingressos-poo.git
```
2. Abra o projeto no IntelliJ IDEA
3. Execute o arquivo `Principal.java` dentro de `venda_ingresso/main`

## Conceitos Aplicados

### Serialização
Objetos da classe `Ingresso` são salvos em arquivo `.ser` via `ObjectOutputStream`
e recuperados via `ObjectInputStream` pela classe `GerenciadorArquivo`.
Ao fechar a janela de compra, os ingressos são serializados automaticamente.

### Multithreading
Compras simultâneas são simuladas com `CompradorRunnable` que implementa `Runnable`.
Os métodos de `GerenciadorIngresso` são protegidos com `synchronized` para evitar
race conditions. Uma thread daemon salva os ingressos automaticamente a cada 500ms.

## Branches

| Branch | O que foi implementado |
|--------|----------------------|
| feature/enums | SetorEnum com nome, valor e limite. Refatoração dos if-else de preço |
| feature/excecoes | QuantidadeInvalidaException e SetorEsgotadoException |
| feature/serializacao | GerenciadorArquivo, serialização e desserialização |
| feature/threads | CompradorRunnable, synchronized, thread daemon |

## Autor

Nome: Ariane Sanga  
Turma: Programação Orientada a Objetos
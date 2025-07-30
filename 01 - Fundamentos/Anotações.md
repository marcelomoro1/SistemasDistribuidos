# Anotações

### Sistemas Distribuidos
- Diferentes arquiteturas de hardware, S.O e linguagens
- Arquitetura Cliente/Servidor ou Ponto a ponto, sendo escalável, com segurança e manutenção
- GRID computacional
- Objetivo de compartilhar recursos: processador e memória, controlando sincronismo
- Comunicação via socket, sendo ela bloqueante, onde é possivel ler ou escrever
- Programação multitarefa com thread (mini processo dentro de um processo)  

### Sistemas paralelos
- Cluster computacional
- Fortemente acoplado (fixos via TCP/IP)
- Arquitetura ponto a ponto, potencial de escalabilidade, manutenção

### Threads
- É a forma com que um processo consegue se dividir em duas ou mais tarefas, executando elas de forma simultânea.
  As threads ocupam o mesmo espaço de endereço e recursos de um mesmo processo

### Processos
- É um módulo executável único (programa), que corre concorrentemente com outros processos.
  Cada processo pode ter multiplas threads

### Threads x Processos
- As threads ocupam o mesmo espaço de endereço e recursos de um mesmo processo, cada processo ocupa um espaço individual
  e não compartilham recursos entre eles
- Threads consomem menos recursos do sistema do que processos
- A comunicação entre threads é fácil e rápida pois compartilham memória e a sua criação e gerenciamento são leves e rápidos
- Nos processos a criação e gerenciamento de processos são mais custosos em termos de tempo e recursos
- Processos tem seu próprio espaço de endereço e recursos, como memória, arquivos e estado do sistema

### Rede Cliente/Servidor
- Arquitetura de rede onde um programa (o cliente) solicita um serviço ou recurso de outro programa (o servidor)
  E o servidor responde a solicitação, fornecendo o serviço ou recurso solicitado.

### Rede Ponto a Ponto (P2P)
- Arquitetur de rede de computadores onde cada nó atua como cliente e servidor simultaneamente,
permitindo que os dispositivos se comuniquem e compartilhem recursos diretamente, sem a necessidade de um servidor central

### Cliente/Servidor x Ponto a Ponto
- Função dos nós: No cliente/servidor o papél é bem definido, o cliente solicita e o servidor responde. No P2P todos nós são iguais em função.
- Centralização: No cliente/servidor o servidor é o ponto central. No P2P é descentralizado ou distribuido.
- Escalabilidade: No cliente/servidor é limitada pela capacidade do servidor. No P2P é alta escalabidade pois cada peer pode compartilhar recursos.
- Adição de nós: No cliente/servidor aumenta a carga do servidor. No P2P pode melhorar o desempenho geral.
- Falhas: No cliente/servidor se o servidor cair o sistema para. No P2P outros peers podem continuar funcionando.
- Segurança/Controle: No cliente/servidor é centralizado e fácil de controlar. No P2P é descentralizado, dificil de administrar.
- Exemplos de aplicações: No cliente/servidor: HTTP; E-mail; Banco de dados. No P2P: BitTorrent, BlockChain

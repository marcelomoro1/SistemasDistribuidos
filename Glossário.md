
# Glossário de Conceitos

---

## Conceitos de Concorrência

- **Thread:** Uma unidade de execução dentro de um processo que compartilha o mesmo espaço de memória. Isso as torna leves e eficientes para tarefas concorrentes.

- **Concorrência:** A capacidade de um sistema lidar com várias tarefas ao mesmo tempo, intercalando o processamento em um único núcleo de CPU. O objetivo é dar a ilusão de paralelismo.

- **Paralelismo:** A execução simultânea de múltiplas tarefas em diferentes processadores ou núcleos de CPU. É a execução real e física de várias tarefas ao mesmo tempo.

- **Condição de Corrida (Race Condition):** Um problema que ocorre quando várias threads tentam acessar e modificar um mesmo recurso compartilhado. O resultado se torna imprevisível, dependendo da ordem de execução das threads.

- **Lock / Mecanismo de Sincronização:** Uma ferramenta usada para prevenir condições de corrida. Um **lock** garante que apenas uma thread possa acessar um recurso crítico por vez. Em Java, isso é feito com a palavra-chave **`synchronized`** ou com classes como **`ReentrantLock`**.

---

## Conceitos de Sistemas Distribuídos

- **Relógio Físico (Physical Clock):** Um relógio que se aproxima do tempo real. Em sistemas distribuídos, os relógios físicos raramente estão perfeitamente sincronizados devido a uma taxa de desvio (_drift_).

- **Relógio Lógico (Logical Clock):** Um mecanismo para ordenar eventos em um sistema distribuído sem depender do tempo físico, focando na relação de "causa e efeito".
    - **Relógio de Lamport:** Atribui um carimbo de tempo (timestamp) a cada evento. Se A ocorre antes de B, o timestamp de A é menor que o de B. Não garante que A ocorreu no tempo real antes de B.
    - **Relógio Vetorial:** Uma evolução do relógio de Lamport que captura a relação causal entre os eventos. Cada processo tem um vetor de carimbos de tempo.

- **Exclusão Mútua (Mutual Exclusion):** Um princípio que garante que, em um dado momento, apenas um processo ou thread tenha acesso a um recurso compartilhado. É essencial para evitar a corrupção de dados.

- **Eleição (Election):** Um algoritmo usado para escolher um processo coordenador ou líder em um sistema distribuído.
    - **Algoritmo do Valentão (Bully Algorithm):** Um processo com falha de líder envia uma mensagem de eleição. Se ele não receber resposta de um processo com identificador maior, ele se declara o novo líder.
    - **Algoritmo em Anel (Ring Algorithm):** Um processo envia uma mensagem de eleição para o próximo no anel, que a repassa. A mensagem circula e o processo com o maior identificador se torna o novo líder.

- **Pool de Threads:** É criado um conjunto fixo ou dinâmico de threads e em vez de criar e destruir threads a todo momento (o que é caro em termos de processamento e memória), o sistema reaproveita threads já existentes.

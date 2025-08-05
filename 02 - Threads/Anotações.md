Tipos de Thread:
Thread de Usuario:
Gerenciadas pelo próprio programa ou biblioteca, sem envolvimento direto do sistema operacional. 
Mais leves e rápidas de criar e alternar, pois não requerem mudanças no contexto do sistema operacional. 
Podem ser implementadas de forma mais flexível, permitindo que o programador controle o agendamento. 
Se uma thread de usuário bloquear, todo o processo que a contém bloqueia, pois o sistema operacional não tem conhecimento das threads individuais

Thread de núcleo:
Gerenciadas diretamente pelo sistema operacional. 
Permitem que o sistema operacional agende a execução de threads individualmente, mesmo que um thread bloqueie, outros threads do mesmo processo podem continuar executando


Thread dentro de thread -> problema com sincronismo
Thread com join para realizar em sequencia




1. Introdução

Java suporta threads desde a versão JDK 1.0. Porém, trabalhar diretamente com a classe Thread pode ser perigoso e se 
mau implementado pode oferecer erros. Com o advento do Java 5 em 2004 foi introduzido a API de java.util.concurrent
visando assim lidar com concorrencias de forma mais segura ao ser implementado threads. Para isso, foi introduzido 
nessa API o conceito de ExecutorService. 
Esse conceito introduziu uma abstração para trabalhar com threads de modo que os executores podem trabalhar
de forma assíncronas e podem gerenciar um pool de threads sem a nessecidade de se criar threas manualmente.
A classe Executors é um factory que implementa varias formas de se trabalhar com threads.

2. Abstração do Spring TaskExecutor

O Spring 2.0 introduziu uma nova abstração para lidar com executores, a TaskExecutor. 
A interface TaskExecutor é identica a interface java.util.concurrent.Executor. Sua principal razão de existir é 
abstrair a necessidade de se utilizar Java 5 ao se utilizar conjuntos de encadeamentos.

A TaskExecutor orginalmente foi desenvolvido para poder oferecer a outros componentes do spring uma abstração
para uso de pool de threads. Componentes com Quartz utiliza TaskExecutor para se trabalhar com pool de threads.

3. Tipos de TaskExecutor
Existe várias implementações de TaskExecutor. Sendo assim existe uma grande probabilidade de você não precisar
implementar um task próprio. 

3.1 
SimpleAsyncTaskExecutor Essa implementação não reutiliza nenhum encadeamento, mas inicia um novo encadeamento para cada chamada. No entanto, ele suporta um limite de simultaneidade que bloqueia qualquer chamada que esteja acima do limite até que um slot seja liberado. Se você está procurando um pool verdadeiro, consulte as discussões SimpleThreadPoolTaskExecutor e ThreadPoolTaskExecutorabaixo.
SyncTaskExecutor Esta implementação não executa invocações de forma assíncrona. Em vez disso, cada chamada ocorre no encadeamento de chamada. É usado principalmente em situações em que a multithread não é necessária, como casos de teste simples.
ConcurrentTaskExecutor Esta implementação é um adaptador para um java.util.concurrent.Executorobjeto. Existe uma alternativa, ThreadPoolTaskExecutorque expõe os Executor parâmetros de configuração como propriedades do bean. É raro precisar usar o ConcurrentTaskExecutor, mas se ThreadPoolTaskExecutornão for flexível o suficiente para suas necessidades, ConcurrentTaskExecutoré uma alternativa.
SimpleThreadPoolTaskExecutor Essa implementação é na verdade uma subclasse de Quartz, SimpleThreadPoolque escuta os retornos de chamada do ciclo de vida do Spring. Isso geralmente é usado quando você tem um conjunto de encadeamentos que talvez precise ser compartilhado pelos componentes Quartz e não Quartz.
ThreadPoolTaskExecutor Essa implementação é a mais usada. Ele expõe as propriedades do bean para configurar um java.util.concurrent.ThreadPoolExecutore envolve-o em um TaskExecutor. Se você precisar se adaptar a um tipo diferente java.util.concurrent.Executor, é recomendável usar um ConcurrentTaskExecutor.
WorkManagerTaskExecutor



https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/scheduling.html

  



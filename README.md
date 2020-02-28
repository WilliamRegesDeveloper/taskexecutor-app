_**1. Introdução**_

Java suporta threads desde a versão JDK 1.0. Porém, trabalhar diretamente com a classe Thread pode ser perigoso e se 
mau implementado pode oferecer erros. Com o advento do Java 5 em 2004 foi introduzido a API de java.util.concurrent
visando assim lidar com concorrencias de forma mais segura ao ser implementado threads. Para isso, foi introduzido 
nessa API o conceito de [ExecutorService](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html). 
Esse conceito introduziu uma abstração para trabalhar com threads de modo que os executores podem trabalhar
de forma assíncronas e podem gerenciar um pool de threads sem a nessecidade de se criar threas manualmente.
A classe [Executors](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executors.html) é um factory que 
implementa varias formas de se trabalhar com threads.

_**2. Abstração do Spring TaskExecutor**_

O Spring 2.0 introduziu uma nova abstração para lidar com executores, a [TaskExecutor](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/core/task/TaskExecutor.html). 
A interface TaskExecutor é identica a interface [java.util.concurrent.Executor](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executor.html)
. Sua principal razão de existir é abstrair a necessidade de se utilizar Java 5 ao se utilizar conjuntos de
 encadeamentos.

A TaskExecutor orginalmente foi desenvolvido para poder oferecer a outros componentes do spring uma abstração
para uso de pool de threads. Componentes com Quartz utiliza TaskExecutor para se trabalhar com pool de threads.

_**3. Tipos de TaskExecutor**_

Existe várias implementações de TaskExecutor. Sendo assim existe uma grande probabilidade de você não precisar
implementar um task próprio. 

**Tipos de TaskExecutor**
 - `SyncTaskExecutor` Esta implementação não executa chamadas de forma assíncrona. Em vez disso, cada chamada ocorre no
   encadeamento de chamada. É usado principalmente em situações em que a multithread não é necessária, como em casos de 
   teste simples;
  
 - `SimpleAsyncTaskExecutor` Essa implementação não reutiliza nenhum encadeamento, mas inicia um novo encadeamento para 
   cada chamada. No entanto, ele suporta um limite de simultaneidade que bloqueia qualquer chamada que esteja acima do 
   limite até que um slot seja liberado. Se você está procurando um pool verdadeiro, veja ThreadPoolTaskExecutorabaixo;
 
 - `ConcurrentTaskExecutor` Esta implementação é um adaptador para uma java.util.concurrent.Executorinstância. Existe uma
   alternativa, ThreadPoolTaskExecutorque expõe os Executor parâmetros de configuração como propriedades do bean.
   Raramente é necessário usar ConcurrentTaskExecutordiretamente, mas se o ThreadPoolTaskExecutoritem não for flexível
   o suficiente para suas necessidades, ConcurrentTaskExecutorserá uma alternativa;
   
 - `ThreadPoolTaskExecutor` Essa implementação é a mais usada. Ele expõe as propriedades do bean para configurar um 
   java.util.concurrent.ThreadPoolExecutore envolve-o em um TaskExecutor. Se você precisar se adaptar a um tipo
   diferente java.util.concurrent.Executor, é recomendável usar um ConcurrentTaskExecutor;
   
 - Outras implementações.

_**4. Bean de TaskExecutor**_

A implementação de uma taskExecutor pode ser obtido ao implementa-lo como uma instancia de bean. A instancia de 
taskexecutor pode ser obtido por meio de métodos fábrica anotados com @Bean em uma classe de configuração anotado
com @Configuration e [@EnableAsync](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html)
. Assim, pode-se utilizar uma anotação [@Async](https://www.baeldung.com/spring-async) 
em métodos de classes de serviços candidados a serem assincronos fazendo com que o spring proxie o método anotado e 
implemente taskexecutor.

_**5. Utilizando TaskExecutor com API Future**_

A interface [Future](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html) introduzino no Java 5 surgiu no intuito de se obter resultado de uma computação assíncrona,
porém não havia métodos que combinasse essas computações ou manipulação de possíveis erros.

No java 8 foi introduzido a classe [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html), implentação da interface Future.
 
CompletableFuture é ao mesmo tempo um componente básico e uma estrutura com cerca de 50 métodos diferentes para
compor, combinar, executar etapas de computação assíncronas e manipular erros.

CompletableFuture pode ser uma ótima estratégia para retorno de resultados quando se deseja trabalhar com TaskExecutor,
por exemplo, implementando ThreadPoolTaskExecutor como pool de threads. Assim, ao trabalhar com multithreads, 
CompletableFuture garante que todos os resultados obtidos podem ser agrupados e retornados ao cliente de requisição.
 
_**6. Teste do exemplo**_
 - Se for por JMeter pode utilizar o arquivo cobrancaemlote.jmx na pasta jmeter no projeto e ver os logs gerado no IDE
   das threads sendo abertas;
   
 - Teste integrado para testar envio em lote e ver os logs gerado no IDE das threads sendo abertas;
 
Material obtido em:

https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/scheduling.html

https://www.baeldung.com/java-future

https://www.baeldung.com/java-completablefuture

  



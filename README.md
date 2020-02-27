Threads

Java suporta threads desde a versão JDK 1.0. Porém, trabalhar diretamente com a classe Thread pode ser perigoso e se 
mau implementado pode oferecer erros. Com o advento do Java 5 em 2004 foi introduzido a API de java.util.concurrent
visando assim lidar com concorrencias de forma mais segura ao ser implementado threads. Para isso, foi introduzido 
nessa API o conceito de ExecutorService. 
Esse conceito introduziu uma abstração para trabalhar com threads de modo que os executores podem trabalhar
de forma assíncronas e podem gerenciar um pool de threads sem a nessecidade de se criar threas manualmente.
A classe Executors é um factory que implementa varias formas de se trabalhar com threads.

Abstração do Spring TaskExecutor
O Spring 2.0 introduziu uma nova abstração para lidar com executores, a TaskExecutor. 



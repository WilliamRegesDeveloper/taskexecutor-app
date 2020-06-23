package br.com.taskexecutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "threadPoolTaskExecutorCustom")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutorCustom() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("custom-");
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(5000);
        executor.setQueueCapacity(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean(name = "syncTaskExecutor")
    public TaskExecutor getSyncTaskExecutor() {
       return new SyncTaskExecutor();
    }

    @Bean(name = "simpleAsyncTaskExecutor")
    public TaskExecutor getSimpleAsyncTaskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean(name = "concurrentTaskExecutor")
    public TaskExecutor getConcurrentTaskExecutor() {
        return new ConcurrentTaskExecutor();
    }
}

package br.com.taskexecutor.repository;

import br.com.taskexecutor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Service
public class UserRepository {

    private final static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Async("threadPoolTaskExecutorCustom")
    public CompletableFuture<User> threadPoolTaskExecutor(String user) {

        logger.info("Thread -> " + Thread.currentThread().getName());
            User userResult = new User();
            userResult.setLogin("123");
            userResult.setId(user);
            userResult.setData(LocalDateTime.now());
        return CompletableFuture.completedFuture(userResult);
    }

    @Async("syncTaskExecutor")
    public CompletableFuture<User> syncTaskExecutor(String user) {
        logger.info("Thread -> " + Thread.currentThread().getName());
        User userResult = new User();
        userResult.setLogin("123");
        userResult.setId(user);
        userResult.setData(LocalDateTime.now());

        return CompletableFuture.completedFuture(userResult);
    }

    @Async("simpleAsyncTaskExecutor")
    public CompletableFuture<User> simpleAsyncTaskExecutor(String user) {
        logger.info("Thread -> " + Thread.currentThread().getName());
        User userResult = new User();
        userResult.setLogin("123");
        userResult.setId(user);
        userResult.setData(LocalDateTime.now());

        return CompletableFuture.completedFuture(userResult);
    }

    @Async("concurrentTaskExecutor")
    public CompletableFuture<User> concurrentTaskExecutor(String user) {
        logger.info("Thread -> " + Thread.currentThread().getName());
        User userResult = new User();
        userResult.setLogin("123");
        userResult.setId(user);
        userResult.setData(LocalDateTime.now());

        return CompletableFuture.completedFuture(userResult);
    }

    public CompletableFuture<User> paralellStream(String user) {
        logger.info("Thread -> " + Thread.currentThread().getName());
        User userResult = new User();
        userResult.setLogin("123");
        userResult.setId(user);
        userResult.setData(LocalDateTime.now());

        return CompletableFuture.completedFuture(userResult);
    }

    public Callable<User> callable(String user) {

        return () -> {
            logger.info("Thread -> " + Thread.currentThread().getName());
            User userResult = new User();
            userResult.setLogin("123");
            userResult.setId(user);
            userResult.setData(LocalDateTime.now());
            return userResult;
        };
    }
}

package br.com.taskexecutor.service;

import br.com.taskexecutor.aspect.ShowCronous;
import br.com.taskexecutor.model.User;
import br.com.taskexecutor.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class UserProcessService {

    private static final Logger logger = LoggerFactory.getLogger(UserProcessService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutorCustom;

    @ShowCronous
    public List<User> processSimpleAsyncTaskExecutor(List<String> nameUsers) {
        List<CompletableFuture<User>> futures = nameUsers
                .stream()
                .map(this.repository::simpleAsyncTaskExecutor)
                .collect(Collectors.toList());
        return extractValueOnCompletableFuture(futures);
    }

    @ShowCronous
    public List<User> processThreadPoolTaskExecutor(List<String> nameUsers) {
        List<CompletableFuture<User>> futures = nameUsers
                .stream()
                .map(this.repository::threadPoolTaskExecutor)
                .collect(Collectors.toList());
        return extractValueOnCompletableFuture(futures);
    }

    @ShowCronous
    public List<User> processSyncTaskExecutor(List<String> nameUsers) {
        List<CompletableFuture<User>> futures = nameUsers
                .stream()
                .map(this.repository::syncTaskExecutor)
                .collect(Collectors.toList());
        return extractValueOnCompletableFuture(futures);
    }

    @ShowCronous
    public List<User> processConcurrentTaskExecutor(List<String> nameUsers) {
        List<CompletableFuture<User>> futures = nameUsers
                .stream()
                .map(this.repository::concurrentTaskExecutor)
                .collect(Collectors.toList());
        return extractValueOnCompletableFuture(futures);
    }

    @ShowCronous
    public List<User> parallelStream(List<String> nameUsers) {
        List<CompletableFuture<User>> futures = nameUsers
                .parallelStream()
                .map(this.repository::paralellStream)
                .collect(Collectors.toList());
        return extractValueOnCompletableFuture(futures);
    }

    @ShowCronous
    public List<User> processCallable(List<String> nameUsers) {

        List<Future<User>> collect =
                nameUsers
                        .stream()
                        .map(this.repository::callable)
                        .map(threadPoolTaskExecutorCustom::submit)
                        .collect(Collectors.toList());
        return collect
                .stream()
                .map(this::mapperToUser)
                .collect(Collectors.toList());
    }

    private User mapperToUser(Future<User> future) {
        User user = null;
        try {
            user = (User) future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    private List<User> extractValueOnCompletableFuture(final List<CompletableFuture<User>> futures) {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        return futures.parallelStream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}

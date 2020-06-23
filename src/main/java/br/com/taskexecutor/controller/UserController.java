package br.com.taskexecutor.controller;

import br.com.taskexecutor.model.User;
import br.com.taskexecutor.service.UserProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserProcessService userProcessService;

    @PostMapping(value = "/process/user/thread/pool/task/executor")
    @ResponseBody
    public List<User> processUserWithThreadPoolTaskExecutor(@RequestBody List<String> nameUsers ) {
        return userProcessService.processThreadPoolTaskExecutor(nameUsers);
    }

    @PostMapping(value = "/process/user/sync/task/executor")
    @ResponseBody
    public List<User> processSyncTaskExecutor(@RequestBody List<String> nameUsers ) {
        return userProcessService.processSyncTaskExecutor(nameUsers);
    }

    @PostMapping(value = "/process/user/simple/async/task/executor")
    @ResponseBody
    public List<User> processSimpleAsyncTaskExecutor(@RequestBody List<String> nameUsers ) {
        return userProcessService.processSimpleAsyncTaskExecutor(nameUsers);
    }

    @PostMapping(value = "/process/user/concurrent/task/executor")
    @ResponseBody
    public List<User> processConcurrentTaskExecutor(@RequestBody List<String> nameUsers ) {
        return userProcessService.processConcurrentTaskExecutor(nameUsers);
    }

    @PostMapping(value = "/process/user/parallel/stream")
    @ResponseBody
    public List<User> parallelStream(@RequestBody List<String> nameUsers ) {
        return userProcessService.parallelStream(nameUsers);
    }

    @PostMapping(value = "/process/user/parallel/callable")
    @ResponseBody
    public List<User> parallelCallable(@RequestBody List<String> nameUsers ) {
        return userProcessService.processCallable(nameUsers);
    }


}

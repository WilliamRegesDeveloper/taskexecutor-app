package br.com.taskexecutor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void processUserWithThreadPoolTaskExecutor() throws Exception {
        mvc.perform(
                post("/v1/process/user/thread/pool/task/executor")
                        .content(buildNamesToProcess())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void processSyncTaskExecutor() throws Exception {
        mvc.perform(
                post("/v1/process/user/sync/task/executor")
                        .content(buildNamesToProcess())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void processSimpleAsyncTaskExecutor() throws Exception {
        mvc.perform(
                post("/v1/process/user/simple/async/task/executor")
                        .content(buildNamesToProcess())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void processConcurrentTaskExecutor() throws Exception {
        mvc.perform(
                post("/v1/process/user/concurrent/task/executor")
                        .content(buildNamesToProcess())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void parallelStream() throws Exception {
        mvc.perform(
                post("/v1/process/user/parallel/stream")
                        .content(buildNamesToProcess())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String buildNamesToProcess() throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(
                        getNames());
    }

    private Object getNames() {
        ArrayList<String> arrays = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrays.add("william franklin");
        }
        return arrays;
    }
}
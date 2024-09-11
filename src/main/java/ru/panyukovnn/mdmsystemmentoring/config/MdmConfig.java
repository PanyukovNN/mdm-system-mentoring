package ru.panyukovnn.mdmsystemmentoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@EnableScheduling
public class MdmConfig {

    @Bean(destroyMethod = "shutdown")
    public ExecutorService mdmEventSendingExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService cleanOldRecordsExecutor() {
        return Executors.newFixedThreadPool(1);
    }

}

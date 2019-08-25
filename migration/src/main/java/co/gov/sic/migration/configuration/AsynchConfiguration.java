package co.gov.sic.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsynchConfiguration {

    @Bean(name = "asynchExecutor")
    public Executor asynchExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}

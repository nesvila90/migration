package co.gov.sic.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@Component
public class SchedulerConfig {

    @Bean("taskExecutor")
    public ConcurrentTaskScheduler taskScheduler() {
        ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
        return new ConcurrentTaskScheduler(localExecutor);
    }

}

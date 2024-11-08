package king_min_ji_server.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class WeekRankingBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ResetWeekRankingTasklet resetWeekRankingTasklet;

    @Bean
    public Job resetWeekRankingJob() {
        return new JobBuilder("resetWeekRankingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(resetWeekRankingStep())
                .build();
    }

    @Bean
    public Step resetWeekRankingStep() {
        return new StepBuilder("resetWeekRankingStep", jobRepository)
                .tasklet(resetWeekRankingTasklet, transactionManager)
                .build();
    }
}

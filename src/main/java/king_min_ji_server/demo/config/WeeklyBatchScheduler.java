package king_min_ji_server.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class WeeklyBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job resetWeekRankingJob;

    @Scheduled(cron = "${task.scheduling.cron.reset-weekly}")
    public void scheduleResetWeekRankingJob() {
        try {
            jobLauncher.run(resetWeekRankingJob, new org.springframework.batch.core.JobParameters());
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로깅
        }
    }
}

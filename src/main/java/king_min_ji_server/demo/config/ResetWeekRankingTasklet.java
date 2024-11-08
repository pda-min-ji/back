package king_min_ji_server.demo.config;

import king_min_ji_server.demo.repository.WeekRankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetWeekRankingTasklet implements Tasklet {

    private final WeekRankingRepository weekRankingRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        weekRankingRepository.deleteAll(); // 모든 데이터를 삭제
        return RepeatStatus.FINISHED;
    }
}

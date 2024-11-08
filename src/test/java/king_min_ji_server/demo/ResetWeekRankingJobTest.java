package king_min_ji_server.demo;

import king_min_ji_server.demo.config.SecurityConfig;
//import king_min_ji_server.demo.config.WebConfig;
import king_min_ji_server.demo.domain.Week_ranking;
import king_min_ji_server.demo.repository.WeekRankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ResetWeekRankingJobTest {

    @Autowired
    private WeekRankingRepository weekRankingRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job resetWeekRankingJob;

    @MockBean
    private SecurityConfig securityConfig;  // SecurityConfig의 빈을 목킹하여 테스트에 포함되지 않도록 함

//    @MockBean
//    private WebConfig webConfig;  // WebConfig의 빈을 목킹하여 테스트에 포함되지 않도록 함

    @BeforeEach
    @Rollback(false)  // 테스트 전에 DB에 데이터가 저장되도록 설정
    void setUp() {
        List<Week_ranking> initialData = Arrays.asList(
                Week_ranking.builder().name("Alice").ranking(1).userId(1L).point(100).imgPath("img1").bojId("Alice123").build(),
                Week_ranking.builder().name("Bob").ranking(2).userId(2L).point(80).imgPath("img2").bojId("Bob123").build(),
                Week_ranking.builder().name("Charlie").ranking(3).userId(3L).point(60).imgPath("img3").bojId("Charlie123").build()
        );

        weekRankingRepository.saveAll(initialData);
    }

    @Test
    void testResetWeekRankingJob() throws Exception {
        assertThat(weekRankingRepository.findAll().size()).isGreaterThan(0);
        JobExecution jobExecution = jobLauncher.run(resetWeekRankingJob, new JobParameters());
        assertThat(jobExecution.getStatus().isUnsuccessful()).isFalse();
        assertThat(weekRankingRepository.findAll().isEmpty()).isTrue();
    }
}

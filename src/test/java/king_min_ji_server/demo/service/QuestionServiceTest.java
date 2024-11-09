package king_min_ji_server.demo.service;

import jakarta.transaction.Transactional;
import king_min_ji_server.demo.repository.QuestionRepository;
import king_min_ji_server.demo.repository.QuestionTagRepository;
import king_min_ji_server.demo.web.dto.QuestionResponse;
import king_min_ji_server.demo.web.dto.TotalPointResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class) // JUnit 5와 Spring 통합
@SpringBootTest
public class QuestionServiceTest {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final QuestionTagRepository questionTagRepository;

    @Autowired
    QuestionServiceTest(QuestionService questionService, QuestionRepository questionRepository, QuestionTagRepository questionTagRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
        this.questionTagRepository = questionTagRepository;
    }

    @BeforeEach
    public void 시작() {
        System.out.println("시작");
    }

    @AfterEach
    public void 종료() {
        System.out.println("종료");
    }

    @Test
    public void JSON파일_DB저장() throws IOException {
        questionService.saveQuestionsFromJsonFile("questionData/output.json");
    }

    @Test
    @Transactional
    public void 테스트조회() {
        List<QuestionResponse> dtos = questionService.getRandomQuestion();

        System.out.println(dtos);
    }

    @Test
    public void 유저가푼문제저장_false() {
        Boolean answer = questionService.saveUserQuestion("jtkim965",1823);

        System.out.println(answer);
    }
    @Test
    public void 유저가푼문제저장_true() {
        Boolean answer = questionService.saveUserQuestion("jtkim965",1003);

        System.out.println(answer);
    }

    @Test
    @Transactional
    public void 유저가푼문제전부가져오기() {
//        List<User_Question> list = questionService.getUserPoints();
        List<TotalPointResponse> list = questionService.getUserPoints();

        for (TotalPointResponse t : list) {
            System.out.println(t.getBojId());
            System.out.println(t.getTotalPoint());
        }
    }

}
package king_min_ji_server.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class) // JUnit 5와 Spring 통합
@SpringBootTest
public class QuestionServiceTest {

    private final QuestionService questionService;

    @Autowired
    QuestionServiceTest(QuestionService questionService) {
        this.questionService = questionService;
    }

    @BeforeEach
    public void 시작() {
        System.out.println("시작");
    }

    @BeforeEach
    public void 종료() {
        System.out.println("종료");
    }

    @Test
    public void JSON파일_DB저장() throws IOException {
        questionService.saveQuestionsFromJsonFile("questionData/output.json");
    }

}
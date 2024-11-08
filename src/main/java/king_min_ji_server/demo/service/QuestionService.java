package king_min_ji_server.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import king_min_ji_server.demo.converter.QuestionConverter;
import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.Tag;
import king_min_ji_server.demo.domain.User;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
import king_min_ji_server.demo.domain.mapping.User_Question;
import king_min_ji_server.demo.repository.*;
import king_min_ji_server.demo.repository.QuestionRepository;
import king_min_ji_server.demo.repository.QuestionTagRepository;
import king_min_ji_server.demo.repository.TagRepository;
import king_min_ji_server.demo.web.dto.QuestionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final QuestionTagRepository questionTagRepository;
    private final QuestionConverter questionConverter;
    private final UserRepository userRepository;
    private final UserQuestionRepository userQuestionRepository;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository, QuestionTagRepository questionTagRepository, QuestionConverter questionConverter, UserRepository userRepository, UserQuestionRepository userQuestionRepository) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
        this.questionTagRepository = questionTagRepository;
        this.questionConverter = questionConverter;
        this.userRepository = userRepository;
        this.userQuestionRepository = userQuestionRepository;
    }
    // output.json DB 슈슉
    @Transactional
    public void saveQuestionsFromJsonFile(String fileName) throws IOException {
        // JSON 파일 읽기
        ClassPathResource resource = new ClassPathResource(fileName);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(resource.getInputStream());

        System.out.println(root.toPrettyString());

        for (JsonNode node : root) {
            // 문제 번호로 Question 엔티티 검색 또는 생성
            int problemNumber = node.get("number").asInt();
            Optional<Question> optionalQuestion = questionRepository.findByNumber(problemNumber);

            Question question = optionalQuestion.orElseGet(() -> Question.builder()
                    .url(node.get("url").asText())
                    .number(problemNumber)
                    .title(node.get("title").asText())
                    .level(node.get("level").asInt())
                    .build()
            );

            // question이 새로 생성된 경우에만 저장
            if (optionalQuestion.isEmpty()) {
                questionRepository.save(question);
            }

            // 태그 처리 및 Question_Tag 생성
            List<Question_Tag> questionTags = new ArrayList<>();
            for (JsonNode tagNode : node.get("tags")) {
                String tagName = tagNode.asText();

                // tagName으로 Tag 검색 또는 생성
                Tag tag = tagRepository.findByTag(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(null, tagName, new ArrayList<>())));

                // Question_Tag 생성
                Question_Tag questionTag = Question_Tag.builder()
                        .question(question)
                        .tag(tag)
                        .build();

                questionTags.add(questionTag);
                questionTagRepository.save(questionTag);
            }

            // Question에 관계된 Tags 저장
            Optional.ofNullable(question.getQuestionTags()).orElse(new ArrayList<>()).addAll(questionTags);
            questionRepository.save(question);
        }
    }

    @Transactional
    public List<QuestionResponse> getRandomQuestion() {
        List<Question> questions = questionRepository.findByRandom3();
        List<QuestionResponse> dtos = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponse dto = questionConverter.QuestionToQuestionResponseDTO(question);
            dtos.add(dto);
        }

        return dtos;
    }

    public Boolean saveUserQuestion(String bojId, int questionNumber) {
        User user = userRepository.findByBojId(bojId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Question question = questionRepository.findByNumber(questionNumber)
                .orElseThrow(() -> new RuntimeException("문제를 찾을 수 없습니다"));

        if (runIsCorrectAnswerJS(bojId, String.valueOf(questionNumber))) {
            User_Question userQuestion = User_Question.builder()
                    .question(question)
                    .user(user)
                    .build();

            if (userQuestionRepository.findByUserAndQuestion(user, question).isPresent()) {
                throw new RuntimeException("사용자가 이미 푼 문제입니다.");
            }

            userQuestionRepository.save(userQuestion);

            return true;
        } else {
            return false;
        }
    }

    private Boolean runIsCorrectAnswerJS(String bojId, String questionNumber) {
        try {
            String jsFilePath = "src/crawling/isCorrectAnswer.js";

            ProcessBuilder processBuilder = new ProcessBuilder("node", jsFilePath, questionNumber, bojId);
            Process process = processBuilder.start();

            // Node.js 프로세스의 출력을 읽어오기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                // JavaScript 실행 결과를 출력하고 결과를 불리언으로 변환
                log.info("OUTPUT = " + output);
                String result = output.toString().trim();
                return Boolean.parseBoolean(result); // 결과가 "true" 또는 "false"일 경우 파싱
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
package king_min_ji_server.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import king_min_ji_server.demo.converter.QuestionConverter;
import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.Tag;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
import king_min_ji_server.demo.repostiory.QuestionRepository;
import king_min_ji_server.demo.repostiory.QuestionTagRepository;
import king_min_ji_server.demo.repostiory.TagRepository;
import king_min_ji_server.demo.web.dto.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private TagRepository tagRepository;
    private QuestionTagRepository questionTagRepository;
    private final QuestionConverter questionConverter;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository, QuestionTagRepository questionTagRepository, QuestionConverter questionConverter) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
        this.questionTagRepository = questionTagRepository;
        this.questionConverter = questionConverter;
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
}
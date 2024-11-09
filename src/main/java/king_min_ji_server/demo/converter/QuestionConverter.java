package king_min_ji_server.demo.converter;

import king_min_ji_server.demo.domain.Question;
import king_min_ji_server.demo.domain.mapping.Question_Tag;
import king_min_ji_server.demo.repository.QuestionTagRepository;
import king_min_ji_server.demo.web.dto.QuestionResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionConverter {
    private final QuestionTagRepository questionTagRepository;

    public QuestionConverter(QuestionTagRepository questionTagRepository) {
        this.questionTagRepository = questionTagRepository;
    }
    public QuestionResponseDTO QuestionToQuestionResponseDTO(Question question) {

        List<String> tags = new ArrayList<>();
        List<Question_Tag> questionTags = questionTagRepository.findByQuestion(question);

        for (Question_Tag questionTag : questionTags) {
            tags.add(questionTag.getTag().getTag());
        }

        return QuestionResponseDTO.builder()
                .id(question.getId())
                .level(question.getLevel())
                .number(question.getNumber())
                .url(question.getUrl())
                .title(question.getTitle())
                .tags(tags)
                .build();
    }
}

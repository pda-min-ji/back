package king_min_ji_server.demo.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import king_min_ji_server.demo.apiPayload.ApiResponse;
import king_min_ji_server.demo.apiPayload.code.status.SuccessStatus;
import king_min_ji_server.demo.service.QuestionService;
import king_min_ji_server.demo.web.dto.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Tag(name = "Question API", description = "Question API")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<QuestionResponse>>> getRandomQuestion() {
        List<QuestionResponse> responses = questionService.getRandomQuestion();
        return ResponseEntity.ok(ApiResponse.onSuccess(responses));
    }
}

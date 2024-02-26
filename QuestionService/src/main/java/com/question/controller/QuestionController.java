package com.question.controller;

import com.question.entity.Question;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Question question){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(question));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(questionService.getById(id));
    }

    @GetMapping("quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId){
        return questionService.getQuestionOfQuiz(quizId);
    }

}

package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz){
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.add(quiz));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getById(id));
    }
}

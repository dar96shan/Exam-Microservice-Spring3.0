package com.question.service;

import com.question.entity.Question;
import com.question.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepo questionRepo;
    @Override
    public Question create(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> getAll() {
        return questionRepo.findAll();
    }

    @Override
    public Question getById(Long id) {
        return questionRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Question not found"));
    }

    @Override
    public List<Question> getQuestionOfQuiz(Long quizId) {
        return questionRepo.findByQuizId(quizId);
    }


}

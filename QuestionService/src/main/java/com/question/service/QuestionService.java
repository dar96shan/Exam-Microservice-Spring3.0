package com.question.service;

import com.question.entity.Question;

import java.util.List;

public interface QuestionService {

    Question create(Question question);

    List<Question> getAll();

    Question getById(Long id);

    List<Question> getQuestionOfQuiz(Long quizId);

}

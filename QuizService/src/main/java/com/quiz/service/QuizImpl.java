package com.quiz.service;

import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepo;
import com.quiz.service.Feign.QuestionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizImpl implements QuizService{

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionClient questionClient;

    private Logger logger = LoggerFactory.getLogger(QuizImpl.class);

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepo.save(quiz);

    }

    @Override
    public List<Quiz> getAll() {

        List<Quiz> quizAll = quizRepo.findAll();

        logger.info("Quiz All : {}",quizAll);

        List<Quiz> collectQuestions = quizAll.stream()
                .map(quiz -> {
                    quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
                    return quiz;
                })
                .collect(Collectors.toList());

        logger.info("After map Quiz All : {}",collectQuestions);

        return quizAll;

    }

    @Override
    public Quiz getById(Long id) {
        Quiz quizById = quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));

        logger.info("Quiz by id :{}",quizById);
        quizById.setQuestions(questionClient.getQuestionsOfQuiz(id));

        return quizById;
    }
}

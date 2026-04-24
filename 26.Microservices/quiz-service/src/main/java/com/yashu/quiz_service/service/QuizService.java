package com.yashu.quiz_service.service;

import com.yashu.quiz_service.dao.QuizDao;
import com.yashu.quiz_service.model.QuestionWrapper;
import com.yashu.quiz_service.model.Quiz;
import com.yashu.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;


    public ResponseEntity<String> createQuiz(String category,int numQ,String title){
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Quiz quiz= quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
//        ResponseEntity<List<QuestionWrapper>> questions =
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses){
//        ResponseEntity<Integer> score =
        return score;
    }
}

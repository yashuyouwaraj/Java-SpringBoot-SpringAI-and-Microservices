package com.yashu.quiz_service.service;

import com.yashu.quiz_service.dao.QuizDao;
import com.yashu.quiz_service.feign.QuizInterface;
import com.yashu.quiz_service.model.QuestionWrapper;
import com.yashu.quiz_service.model.Quiz;
import com.yashu.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category,int numQ,String title){
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Optional<Quiz> optionalQuiz = quizDao.findById(id);
        if(optionalQuiz.isEmpty()){
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }

        Quiz quiz= optionalQuiz.get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses){
        if(!quizDao.existsById(id)){
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }

        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}

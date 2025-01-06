package com.sample.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.QuizApp.Question;
import com.sample.QuizApp.QuestionWrapper;
import com.sample.QuizApp.Quiz;
import com.sample.QuizApp.Response;
import com.sample.QuizApp.Repository.QuestionDao;
import com.sample.QuizApp.Repository.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		
		List<Question> questions=questionDao.findRandomQuestionsByCatgeory(category,numQ);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
			Optional<Quiz> quiz=quizDao.findById(id);
	        List<Question> dbquestions=quiz.get().getQuestions();
	        List<QuestionWrapper> userquestions=new ArrayList<>();
	        for(Question q:dbquestions) {
	        	QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
	        	userquestions.add(qw);
	        }
	        	 	
	        return new ResponseEntity<>(userquestions,HttpStatus.OK);
	}

	public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get(); //[10,4,6,8,20]
		List<Question> questions=quiz.getQuestions();// [whf,gtu,y7u,8654,455]
		int score=0;
		int i=0;
		
		for(Response response: responses) {
			
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				score++;
			}
			i++;
		}
		
		return new ResponseEntity<>(score,HttpStatus.OK);
	}
	

}

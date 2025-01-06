package com.sample.QuizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.QuizApp.Question;
import com.sample.QuizApp.Repository.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>>getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsbyCategory(String category) {
		try {
		return new ResponseEntity<>(questionDao.findAllByCategory(category),HttpStatus.OK);
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	
	}

	public ResponseEntity<String> addQuestion(Question question) {
		
		try {
			questionDao.save(question);
			return new ResponseEntity<>("success",HttpStatus.CREATED);
		}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		return new ResponseEntity<>("Failed to Add",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> updateQuestion(Question question) {
		try {
			
		  if (question.getId() == null) {
	            throw new IllegalArgumentException("ID must not be null for updating a product.");
	        }
		return new ResponseEntity<>(questionDao.save(question),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(question,HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> deleteQuestion(int id) {
		try {
			  if (questionDao.existsById(id)) {
			questionDao.deleteById(id);
			return new ResponseEntity<>("Successful",HttpStatus.OK);
			  }
		}
			catch(Exception e) {
				e.printStackTrace();
			}
		 return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
		
	}

}

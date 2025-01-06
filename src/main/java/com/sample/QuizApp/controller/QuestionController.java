package com.sample.QuizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.QuizApp.Question;
import com.sample.QuizApp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("allquestions")
	public ResponseEntity< List<Question>> allQuestions()
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> QuestionsbyCategory(@PathVariable String category){
		
		return questionService.getQuestionsbyCategory(category);
	}

	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question)
	{
		return questionService.addQuestion(question);
	}
	
	@PutMapping("update")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
		return questionService.deleteQuestion(id);
	}
}

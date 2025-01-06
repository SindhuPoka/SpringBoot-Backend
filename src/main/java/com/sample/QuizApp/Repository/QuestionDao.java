package com.sample.QuizApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.QuizApp.Question;

public interface QuestionDao extends JpaRepository<Question,Integer>{
   List<Question> findAllByCategory(String category);

   @Query(value="SELECT * FROM QUESTION q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery=true)

List<Question> findRandomQuestionsByCatgeory(String category, Integer numQ);
}

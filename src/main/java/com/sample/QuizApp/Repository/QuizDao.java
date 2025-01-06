package com.sample.QuizApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.QuizApp.Quiz;

@Repository
public interface QuizDao extends JpaRepository <Quiz,Integer>{
    
}

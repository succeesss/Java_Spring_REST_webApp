package com.example.demo2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface AnswerRepository extends JpaRepository<Answer, Long>{

}

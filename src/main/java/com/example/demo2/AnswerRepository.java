package com.example.demo2;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface AnswerRepository extends JpaRepository<Answer, Long>{

    @Query("SELECT t FROM Answer t WHERE t.answer = ?1 AND t.question_id = ?2 order by t.id")
    List<Answer> findByAnswerAndQuestion_idOrderById(String str, Long id);

}

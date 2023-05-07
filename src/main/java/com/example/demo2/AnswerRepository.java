package com.example.demo2;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface AnswerRepository extends JpaRepository<Answer, Long>{
    /***
     *
     * @param str ответ на вопрос
     * @param id айди вопроса
     * @return находим все вопросы, на которые дан тот или иной ответ (1,2,3 и тд.))
     */
    @Query("SELECT t FROM Answer t WHERE t.answer = ?1 AND t.question_id = ?2 order by t.id")
    List<Answer> findByAnswerAndQuestion_idOrderById(String str, Long id);

}

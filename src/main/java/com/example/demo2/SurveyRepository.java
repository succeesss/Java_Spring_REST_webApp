package com.example.demo2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>{
    @Query("SELECT p FROM Survey p WHERE CONCAT(p.id, '', p.name) LIKE %?1%")
    List<Survey> search(String keyword);

}

package com.example.demo2;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long question_id;
    private Long survey_id;
    private Long answer;

    protected Answer(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Long getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Long survey_id) {
        this.survey_id = survey_id;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }


}

package com.example.demo2;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class VisualForm {

    List<List> answers = new ArrayList<>();
    Question question = new Question();

    public List<List> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List> answers) {
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
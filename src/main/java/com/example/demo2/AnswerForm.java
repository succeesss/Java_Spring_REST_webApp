package com.example.demo2;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AnswerForm {

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
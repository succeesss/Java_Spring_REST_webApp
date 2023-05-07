package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private QuestionRepository Qrepo;

    public QuestionService(){}

    public void save(Question question){
        this.Qrepo.save(question);
    }

    public  Question getByID(Long id) {
        return this.Qrepo.findById(id).get();
    }

    public List<Question> getAll(){
        return this.Qrepo.findAll();
    }

    public List<Question> findBySurveyId(Long id){return this.Qrepo.findQuestionBySurveyId(id);}

    public void deleteByID(Long id) {
        this.Qrepo.deleteById(id);
    }

}

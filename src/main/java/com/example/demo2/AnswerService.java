package com.example.demo2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnswerService {

    private final AnswerRepository repoA;

    @Autowired
    public AnswerService(AnswerRepository repoA){this.repoA = repoA;}



    public void save(Answer answer){
        this.repoA.save(answer);
    }

    public void saveAll(List<Answer> answer){
        this.repoA.saveAll(answer);
    }


    public Answer getByID(Long id) {
        return (Answer) this.repoA.findById(id).get();
    }
    public List<Answer> getAll(Answer answer){
        return this.repoA.findAll();
    }

    public void deleteByID(Long id) {
        this.repoA.deleteById(id);
    }

    public List<Answer> findByAnswerAndQuestion_id(String str, Long id){return this.repoA.findByAnswerAndQuestion_idOrderById(str, id);}
}

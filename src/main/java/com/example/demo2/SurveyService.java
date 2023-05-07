package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repoS;

    public SurveyService(){}

    public List<Survey> listAll(String keyword){ //Коллекция и метод отвечающая за поиск и фильтр в нашей системе
        if (keyword != null){ // Если ключевое слово = null, то вызываем метод из первого return (вывод всех значений)
            return repoS.search(keyword);
        }
        return repoS.findAll(); // Ничего не выведется
    }

    public void save(Survey survey){
        this.repoS.save(survey);
    }

    public List<Survey> getAll(){
        return this.repoS.findAll();
    }

    public Survey getByID(Long id){
        return this.repoS.findById(id).get();
    }

    public void deleteByID(Long id) {
        this.repoS.deleteById(id);
    }
}

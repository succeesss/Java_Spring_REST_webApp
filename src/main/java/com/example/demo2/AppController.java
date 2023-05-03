package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

    @Autowired
    private SurveyService serviceS;

    @Autowired
    private QuestionService serviceQ;

    @Autowired
    private AnswerService serviceA;


    @Autowired
    public AppController(SurveyService serviceS, QuestionService serviceQ, AnswerService serviceA) {
        this.serviceS = serviceS;
        this.serviceQ= serviceQ;
        this.serviceA = serviceA;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Survey> listSurveys = serviceS.listAll(keyword);
        model.addAttribute("listSurveys", listSurveys);
        model.addAttribute("keyword", keyword);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals("admin2")){
            return "index";
        }
        else {return "index2";}
    }

    @RequestMapping("/new")
    public String showNewSurvey(Model model){
        Survey survey = new Survey();
        model.addAttribute("Survey", survey);
        return "new_survey";
    }

    @RequestMapping("/quiz/{id}")
    public String showQuizForm(Model model, @PathVariable(name="id")Long id){
        List<Question> listQuestions = serviceQ.findBySurveyId(id);
        Survey survey = serviceS.getByID(id);
        Answer answer = new Answer();
        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("Answer", answer);
        model.addAttribute("Survey", survey);
        return "quiz";
    }

    @RequestMapping(value = "/saveQuiz", method = RequestMethod.POST)
    public String saveQuiz(@ModelAttribute("Answer") Answer answer){

        serviceA.save(answer);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSurvey(@ModelAttribute("Survey") Survey survey){
        this.serviceS.save(survey);
        return "redirect:/edit/"+survey.getId();
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String showAuthor(){
        return "author";
    }

    @RequestMapping("/edit/{id}")
    public String showNewQuestion(Model model, @PathVariable(name = "id") Long id){
        List<Question> listQuestions = serviceQ.findBySurveyId(id);
        Question question = new Question();
        Survey survey = serviceS.getByID(id);
        question.setSurveyId(id);
        model.addAttribute("Survey", survey);
        model.addAttribute("Question",question);
        model.addAttribute("listQuestions",listQuestions);
        return "new_question";
    }

    @RequestMapping("/editQuestion/{id}")
    public ModelAndView showEditQuestion(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_question");
        Question question = this.serviceQ.getByID(id);
        mav.addObject("Question", question);
        return mav;
    }

    @RequestMapping("/editSurvey/{id}")
    public ModelAndView showSurveyQuestion(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_survey");
        Survey survey = this.serviceS.getByID(id);
        mav.addObject("Survey", survey);
        return mav;
    }


    @RequestMapping(value = "/saveQuestion/{id}", method = RequestMethod.POST)
    public String saveQuestion(@PathVariable(name = "id") Long id,@ModelAttribute("Question") Question question){
        question.setSurveyId(id);
        this.serviceQ.save(question);
        return "redirect:/edit/"+id;
    }

    @RequestMapping("/delete/{id}")
    public String deleteSurvey(@PathVariable(name = "id") Long id){
        serviceS.deleteByID(id);
        return "redirect:/";
    }

    @RequestMapping("/deleteQuestion/{id}")
    public String deleteQuestion(HttpServletRequest request, @PathVariable(name = "id") Long id){
        serviceQ.deleteByID(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @RequestMapping("/new_formquestion/{id}")
    public String showNewFormQuestion(@PathVariable(name = "id") Long id, Model model){
        Question question = new Question();
        Survey survey = serviceS.getByID(id);
        model.addAttribute("Survey", survey);
        model.addAttribute("Question", question);
        return "new_formQuestion";
    }

}

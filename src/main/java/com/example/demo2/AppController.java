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

    /***
     *
     * @param model загружаем атрибуты: все опросы, ключевое слово - для поиска
     * @param keyword - ключевое слово
     * @return
     */
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

    // создание нового опроса
    @RequestMapping("/new")
    public String showNewSurvey(Model model){
        Survey survey = new Survey();
        model.addAttribute("Survey", survey);
        return "new_survey";
    }

    /***
     *
     * @param model загружаем атрибуты: aForm(содержит все вопросы) и потом заполняем ее ответами, которые даст пользователь
     * @param id - id опроса, который мы проходим
     * @return
     */
    @RequestMapping("/quiz/{id}")
    public String showQuizForm(Model model, @PathVariable(name="id")Long id){
        List<Question> listQuestions = serviceQ.findBySurveyId(id);
        Survey survey = serviceS.getByID(id);
        List<Answer> answers = new ArrayList<>();
        for (Question i:listQuestions){
            Answer answer = new Answer();
            answers.add(answer);
        }
        AnswerForm aForm = new AnswerForm();
        aForm.setQuestions(listQuestions);
        aForm.setAnswers(answers);
        model.addAttribute("aForm", aForm);
        model.addAttribute("Survey", survey);

        return "quiz";
    }

    /***
     *
     * @param aForm - достаем из ответа (пример 2/33/4 (где 2 - ответ на вопрос, 33 - id опроса, 4 - id вопроса)) и распределяем по таблице и записываем в БД
     * @return
     */

    @RequestMapping(value = "/saveQuiz", method = RequestMethod.POST)
    public String saveQuiz(@ModelAttribute("aForm") AnswerForm aForm){
        Long idSurvey;
        Long idQues;
        for (Answer i:aForm.getAnswers()){
            idSurvey = Long.parseLong(i.getAnswer().split("/")[1]);
            idQues = Long.parseLong(i.getAnswer().split("/")[2]);
            i.setAnswer(i.getAnswer().split("/")[0]);
            i.setSurvey_id(idSurvey);
            i.setQuestion_id(idQues);
            serviceA.save(i);
        }
        return "redirect:/";
    }

    //сохранение опроса
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSurvey(@ModelAttribute("Survey") Survey survey){
        this.serviceS.save(survey);
        return "redirect:/edit/"+survey.getId();
    }

    // страница об авторе
    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String showAuthor(){
        return "author";
    }

    /***
     *
     * @param model
     * @param id id опроса
     * @return возвращаем страницу со всеми вопросами, где можно их редактировать/посмотреть визуализацию и тд.
     */
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

    // редактирование вопроса
    @RequestMapping("/editQuestion/{id}")
    public ModelAndView showEditQuestion(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_question");
        Question question = this.serviceQ.getByID(id);
        mav.addObject("Question", question);
        return mav;
    }

    // редактирование опроса
    @RequestMapping("/editSurvey/{id}")
    public ModelAndView showSurveyQuestion(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_survey");
        Survey survey = this.serviceS.getByID(id);
        mav.addObject("Survey", survey);
        return mav;
    }

    // сохранение вопроса
    @RequestMapping(value = "/saveQuestion/{Surveyid}", method = RequestMethod.POST)
    public String saveQuestion(@PathVariable(name = "Surveyid") Long id,@ModelAttribute("Question") Question question){
        question.setSurveyId(id);
        this.serviceQ.save(question);
        return "redirect:/edit/"+id;
    }

    // удаление опроса
    @RequestMapping("/delete/{id}")
    public String deleteSurvey(@PathVariable(name = "id") Long id){
        serviceS.deleteByID(id);
        return "redirect:/";
    }

    // удаление вопроса
    @RequestMapping("/deleteQuestion/{id}")
    public String deleteQuestion(HttpServletRequest request, @PathVariable(name = "id") Long id){
        serviceQ.deleteByID(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    // создание нового вопроса
    @RequestMapping("/new_formquestion/{id}")
    public String showNewFormQuestion(@PathVariable(name = "id") Long id, Model model){
        Question question = new Question();
        Survey survey = serviceS.getByID(id);
        model.addAttribute("Survey", survey);
        model.addAttribute("Question", question);
        return "new_formQuestion";
    }

    /***
     * страница с визуализацией ответов на один вопрос
     * @param id - id вопроса
     * @param model - vForm - содержит вопрос и список, с количеством ответов на каждый вариант ответа
     * @return
     */
    @RequestMapping("/visual/{id}")
    public String showVisual(@PathVariable(name="id")Long id, Model model){
        VisualForm vForm = new VisualForm();
        Question question = serviceQ.getByID(id);
        List<Integer> values = new ArrayList<>();
        List <String> titles = new ArrayList<>();

        for(int j = 1; j<=4; j++){   // получение количества ответов на каждый вариант ответа
            values.add(serviceA.findByAnswerAndQuestion_id(String.valueOf(j), question.getId()).size()) ;
        }
            titles.add(question.getAns1());
            titles.add(question.getAns2());
            titles.add(question.getAns3());
            titles.add(question.getAns4());

            vForm.answers.add(new ArrayList<Integer>(values));



        vForm.setQuestion(question);
        model.addAttribute("vForm", vForm);

        return "visual";
    }

}

package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { // Контроллер для отображения страниц аутентификации

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

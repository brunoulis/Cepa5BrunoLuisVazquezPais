package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class indexController {
    @GetMapping("/")
    public String index() {
        return "Hola Mundo bienvenido a cepa5";
    }

    @GetMapping("/about")
    public String about() {
        return "Hola soy un trabajo en spring boot llamado cepa5 hecho por Bruno Luis Vazquez Pais";
    }

}

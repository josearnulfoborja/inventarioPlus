package com.example.InventarioPlus.controller;  // ← Mismo paquete base

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // ← Esta anotación lo hace detectable
public class Homecontroller {

    @GetMapping("/")  // ← Responde a http://localhost:8080/
    public String home(Model model) {
        model.addAttribute("mensaje", "¡InventarioPlus está funcionando!......rrrrr.......");
        return "index";  // ← Busca src/main/resources/templates/index.html
    }
}
package com.example.InventarioPlus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        return "redirect:/login.html";
    }

    @GetMapping("/login.htm")
    public String redirectLegacyLogin() {
        // Compatibilidad con enlaces antiguos que apuntan a .htm
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model,
            HttpSession session) {
        
        System.out.println("Login form submitted!");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        
        model.addAttribute("mensaje", "Login recibido correctamente");
        
        return "redirect:/login.html";
    }
}

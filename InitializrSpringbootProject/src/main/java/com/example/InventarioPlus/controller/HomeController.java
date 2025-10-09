package com.example.InventarioPlus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Forward to the JSP index so container serves it
        return "forward:/index.jsp";
    }
}

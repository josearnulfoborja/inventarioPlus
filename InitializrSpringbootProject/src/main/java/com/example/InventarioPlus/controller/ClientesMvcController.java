package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Cliente;
import com.example.InventarioPlus.service.ClienteJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/mvc/clientes")
public class ClientesMvcController {

    private final ClienteJpaService clienteService;

    public ClientesMvcController(ClienteJpaService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "/clientes.jsp";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "/cliente_form.jsp";
    }

    @PostMapping
    public String create(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.save(cliente);
        redirectAttributes.addFlashAttribute("success", "Cliente registrado correctamente.");
        return "redirect:/mvc/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Optional<Cliente> c = clienteService.findById(id);
        if (c.isPresent()) {
            model.addAttribute("cliente", c.get());
            return "/cliente_form.jsp";
        }
        return "redirect:/mvc/clientes";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.save(cliente);
        redirectAttributes.addFlashAttribute("success", "Cliente actualizado correctamente.");
        return "redirect:/mvc/clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        clienteService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente.");
        return "redirect:/mvc/clientes";
    }
}

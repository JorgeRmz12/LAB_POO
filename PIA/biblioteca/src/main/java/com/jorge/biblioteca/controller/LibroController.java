/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.controller;

import com.jorge.biblioteca.model.libro;
import com.jorge.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibroController {

    @Autowired
    private LibroRepository repository;

    @GetMapping("/")
    public String verInicio(Model model) {
        model.addAttribute("listaLibros", repository.findAll());
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libro", new libro());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute libro libro) {
        repository.save(libro);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarLibro(@PathVariable Long id, Model model) {
        libro libro = repository.findById(id).orElse(null);
        model.addAttribute("libro", libro);
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
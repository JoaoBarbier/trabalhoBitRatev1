package com.bitrate.BitRate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Futuras Implementações
@Controller
@RequestMapping("/moderador")
public class ModeradorController {

    @GetMapping("/painel")
    public String exibirPainelModerador(Model model) {
        return "painel-moderador";
    }
}
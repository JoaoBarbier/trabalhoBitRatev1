package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/nova")
    public String exibirFormularioAvaliacao(@RequestParam Long restauranteId, Model model) {
        Avaliacao novaAvaliacao = new Avaliacao();
        model.addAttribute("avaliacao", novaAvaliacao);
        model.addAttribute("restauranteId", restauranteId);
        return "cadastro-avaliacao";
    }

    @PostMapping("/nova")
    public String salvarAvaliacao(@ModelAttribute Avaliacao avaliacao) {
        avaliacaoService.salvar(avaliacao);
        return "redirect:/restaurantes/" + avaliacao.getRestaurante().getIdRestaurante(); 
    }
}
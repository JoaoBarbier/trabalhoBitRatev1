package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.service.RestauranteService;
import com.bitrate.BitRate.repository.AvaliacaoRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // Lista com todos Restaurantes
    @GetMapping
    public String listarRestaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteService.listarTodos());
        return "restaurantes"; 
    }

    // Cadastrar Novos Restaurantes
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/";
        model.addAttribute("restaurante", new Restaurante());
        return "cadastro-restaurante"; 
    }

    @PostMapping("/novo")
    public String salvarRestaurante(@ModelAttribute Restaurante restaurante) {
        restauranteService.salvar(restaurante);
        return "redirect:/dashboard";
    }

    // Ver Restaurantes Especificos
    @GetMapping("/{id}")
    public String detalhesRestaurante(@PathVariable Long id, Model model, HttpSession session) {
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";

        Restaurante restaurante = restauranteService.buscarPorId(id);
        if (restaurante != null) {
            model.addAttribute("restaurante", restaurante);
            model.addAttribute("avaliacoes", restaurante.getAvaliacoes());
            model.addAttribute("usuario", logado);
            return "detalhes-restaurante";
        }
        return "redirect:/dashboard";
    }

    // Futuras Implementações
    @PostMapping("/avaliar")
    public String salvarAvaliacao(@RequestParam Long idRestaurante, 
                                  @RequestParam int nota, 
                                  @RequestParam String comentario, 
                                  HttpSession session) {
        
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";

        Restaurante restaurante = restauranteService.buscarPorId(idRestaurante);
        
        if (restaurante != null) {
            Avaliacao nova = new Avaliacao();
            nova.setNota(nota);
            nova.setTextoAvaliacao(comentario);
            nova.setDataPublicacao(LocalDateTime.now());
            nova.setCliente(logado);
            nova.setRestaurante(restaurante);

            avaliacaoRepository.save(nova); 
        }

        return "redirect:/restaurante/" + idRestaurante;
    }
}
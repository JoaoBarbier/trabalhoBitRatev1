package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.service.RestauranteService;
import com.bitrate.BitRate.repository.ClienteRepository;
import com.bitrate.BitRate.repository.AvaliacaoRepository;
import com.bitrate.BitRate.repository.RestauranteRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    // Tela inicial
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";
        
        List<Restaurante> restaurantes = restauranteService.listarTodos();
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("usuario", logado);
        return "dashboard";
    }

    // Perfil do Usuario
    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session) {
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");

        if (logado == null) return "redirect:/";

        return clienteRepository.findById(logado.getIdCliente()).map(cliente -> {
            model.addAttribute("usuario", cliente);
            model.addAttribute("avaliacoes", cliente.getAvaliacoes() != null ? cliente.getAvaliacoes() : List.of());
            return "perfil";
        }).orElse("redirect:/");
    }

    // Adicionar novo Restaurante (Mudar para RestaurantesController no futuro)
    @GetMapping("/dashboard/novo")
    public String telaCadastroRestaurante(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/";
        model.addAttribute("restaurante", new Restaurante());
        return "cadastro-restaurante";
    }
    
    @PostMapping("/dashboard/salvar")
    public String salvarRestaurante(Restaurante restaurante, 
                                    @RequestParam(required = false) String comentario,
                                    @RequestParam(required = false) Integer nota,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
    
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";

        Restaurante existente = restauranteRepository.findByNomeAndEndereco(
            restaurante.getNome(), 
            restaurante.getEndereco()
        );

        if (existente != null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este restaurante já foi cadastrado!");
            return "redirect:/dashboard/novo";
        }

        if (nota != null && nota > 0) {
            restaurante.setNotaMedia(nota.doubleValue()); 
        }
    
        restauranteService.salvar(restaurante);

        if (nota != null && nota > 0) {
            Avaliacao aval = new Avaliacao();
            aval.setTextoAvaliacao(comentario);
            aval.setNota(nota);
            aval.setCliente(logado);
            aval.setRestaurante(restaurante);
            aval.setDataPublicacao(LocalDateTime.now());
            avaliacaoRepository.save(aval);
        }

        return "redirect:/dashboard";
    }
}
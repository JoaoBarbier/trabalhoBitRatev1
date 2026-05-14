package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.model.Avaliacao; // Nome corrigido
import com.bitrate.BitRate.service.RestauranteService;
import com.bitrate.BitRate.repository.ClienteRepository;
import com.bitrate.BitRate.repository.AvaliacaoRepository; // Nome corrigido
import com.bitrate.BitRate.repository.RestauranteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository; // Injetando o repositório correto

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";
        
        List<Restaurante> restaurantes = restauranteService.listarTodos();
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("usuario", logado);
        return "dashboard";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session) {
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");

        if (logado == null) {
            return "redirect:/";
    }

        // Buscamos o cliente do repositório para garantir que o Hibernate traga os dados
        return clienteRepository.findById(logado.getIdCliente()).map(cliente -> {
        model.addAttribute("usuario", cliente);
        // Se a lista for nula, passamos uma lista vazia para o Thymeleaf não quebrar
        model.addAttribute("avaliacoes", cliente.getAvaliacoes() != null ? cliente.getAvaliacoes() : List.of());
        return "perfil";
    }).orElse("redirect:/");
    }

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
                                    RedirectAttributes redirectAttributes) { // Adicionado aqui
    
        Cliente logado = (Cliente) session.getAttribute("usuarioLogado");
        if (logado == null) return "redirect:/";

        // Verifica se a unidade já existe (Nome + Endereço)
        Restaurante existente = restauranteRepository.findByNomeAndEndereco(
            restaurante.getNome(), 
            restaurante.getEndereco()
        );

        if (existente != null) {
            // Envia a mensagem de erro para a tela
            redirectAttributes.addFlashAttribute("mensagemErro", "Este restaurante já foi cadastrado!");
            return "redirect:/dashboard/novo"; // Volta para o formulário
        }

        // Se for novo, segue o jogo...
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
            aval.setDataPublicacao(java.time.LocalDateTime.now());
            avaliacaoRepository.save(aval);
        }

        return "redirect:/dashboard";
    }

}
package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Tela de Login
    @GetMapping("/")
    public String exibirLogin() {
        return "index";
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            session.setAttribute("usuarioLogado", cliente);
            return "redirect:/dashboard";
        }
        
        return "redirect:/?erro=usuario_invalido";
    }

    // Pagina de Criar usuario
    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "cadastro";
    }

    @PostMapping("/salvar-usuario")
    public String salvarUsuario(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        
        if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este CPF já está cadastrado!");
            redirectAttributes.addFlashAttribute("cliente", cliente); 
            return "redirect:/cadastro";
        }

        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este e-mail já está em uso!");
            redirectAttributes.addFlashAttribute("cliente", cliente);
            return "redirect:/cadastro";
        }

        cliente.setTipo("USER");
        clienteRepository.save(cliente);
        
        return "redirect:/?sucesso=cadastrado";
    }

    // Deslogar da Conta
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
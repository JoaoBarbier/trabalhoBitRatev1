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
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Import necessário

@Controller
public class AuthController {

    @Autowired
    private ClienteRepository clienteRepository;

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

    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        // Se o modelo já contiver um cliente (vindo de um erro), não sobrescrevemos
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "cadastro";
    }

    @PostMapping("/salvar-usuario")
    public String salvarUsuario(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        
        // 1. Validar CPF duplicado
        if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este CPF já está cadastrado!");
            redirectAttributes.addFlashAttribute("cliente", cliente); // Mantém os dados digitados
            return "redirect:/cadastro";
        }

        // 2. Validar E-mail duplicado
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este e-mail já está em uso!");
            redirectAttributes.addFlashAttribute("cliente", cliente);
            return "redirect:/cadastro";
        }

        // 3. Se estiver tudo OK, define o tipo e salva
        cliente.setTipo("USER");
        clienteRepository.save(cliente);
        
        return "redirect:/?sucesso=cadastrado";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
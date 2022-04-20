package br.com.challange.videos.videos.controller;

import br.com.challange.videos.videos.model.Usuario;
import br.com.challange.videos.videos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class SignUpController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/signup")
    public String showingSignUpForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/signup";

    }

    @PostMapping("/process_register")
    public String processRegister(Usuario usuario) {
        String txt = usuario.getUsername();
        Usuario byUsername = usuarioRepository.findByUsername(txt);
        if (byUsername == null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encoderPassowrd = bCryptPasswordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encoderPassowrd);
            usuarioRepository.save(usuario);
            return "/register_success";
        }
        return "/signup";
    }
}


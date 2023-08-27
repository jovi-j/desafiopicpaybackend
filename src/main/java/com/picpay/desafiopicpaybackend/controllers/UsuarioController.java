package com.picpay.desafiopicpaybackend.controllers;

import java.util.List;
import com.picpay.desafiopicpaybackend.domain.Usuario;
import com.picpay.desafiopicpaybackend.dtos.Usuario.UsuarioViewDTO;
import com.picpay.desafiopicpaybackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @PostMapping
    public UsuarioViewDTO criarUsuario(@RequestBody final Usuario usuario) {
        return new UsuarioViewDTO(usuarioService.saveUsuario(usuario));
    }

}

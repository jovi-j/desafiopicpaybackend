package com.picpay.desafiopicpaybackend.services;

import com.picpay.desafiopicpaybackend.domain.Usuario;
import com.picpay.desafiopicpaybackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public List<Usuario> findAllUsuarios() {
        return repository.findAll();
    }

    public Usuario findByDocumento(String documento) {
        return repository.findUsuarioByDocumento(documento).orElseThrow();
    }

    public Usuario findById(Long id) {
        return repository.findUsuarioById(id).orElseThrow();
    }

    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }
}

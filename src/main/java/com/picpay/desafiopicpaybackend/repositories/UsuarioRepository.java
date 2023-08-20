package com.picpay.desafiopicpaybackend.repositories;

import com.picpay.desafiopicpaybackend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findUsuarioById(Long id);

    public Optional<Usuario> findUsuarioByDocumento(String documento);
}

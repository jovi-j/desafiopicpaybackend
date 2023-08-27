package com.picpay.desafiopicpaybackend.dtos.Usuario;

import com.picpay.desafiopicpaybackend.domain.Usuario;

/**
 * UsuarioViewDTO
 */
public record UsuarioViewDTO(String nome, String email, String docmento, String tipoUsuario) {

    public UsuarioViewDTO(Usuario u) {
        this(u.getNome(), u.getEmail(), u.getDocumento(), u.getTipoUsuario().name());
    }

}

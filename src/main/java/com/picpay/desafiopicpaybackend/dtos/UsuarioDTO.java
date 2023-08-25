package com.picpay.desafiopicpaybackend.dtos;

import java.math.BigDecimal;

import com.picpay.desafiopicpaybackend.domain.enums.TipoUsuario;

public record UsuarioDTO(String nome, String email, String senha, String documento, BigDecimal saldo,
        TipoUsuario tipoUsuario) {
}

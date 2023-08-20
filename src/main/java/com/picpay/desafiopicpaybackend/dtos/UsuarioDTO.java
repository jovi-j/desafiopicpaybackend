package com.picpay.desafiopicpaybackend.dtos;

import com.picpay.desafiopicpaybackend.domain.TipoUsuario;

import java.math.BigDecimal;

public record UsuarioDTO(String nome, String email, String senha, String documento, BigDecimal saldo,
                      TipoUsuario tipoUsuario) {
}

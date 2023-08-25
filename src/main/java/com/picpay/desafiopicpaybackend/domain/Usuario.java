package com.picpay.desafiopicpaybackend.domain;

import com.picpay.desafiopicpaybackend.domain.enums.TipoUsuario;
import com.picpay.desafiopicpaybackend.dtos.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "usuario")
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String documento;

    private BigDecimal saldo;

    private TipoUsuario tipoUsuario;

    public Usuario(UsuarioDTO usuario) {
        this.nome = usuario.nome();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.documento = usuario.documento();
        this.saldo = usuario.saldo();
        this.tipoUsuario = usuario.tipoUsuario();
    }
}

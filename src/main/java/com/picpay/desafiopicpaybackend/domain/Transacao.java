package com.picpay.desafiopicpaybackend.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "transacao")
@Table(name = "transacoes")
public class Transacao {

    public Transacao(Usuario remetente, Usuario destinatario, BigDecimal valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
        this.timestamp = LocalDateTime.now();
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    private LocalDateTime timestamp;

    private BigDecimal valor;

}

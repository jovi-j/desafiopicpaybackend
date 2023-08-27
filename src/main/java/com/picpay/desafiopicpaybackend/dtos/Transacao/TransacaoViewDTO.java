package com.picpay.desafiopicpaybackend.dtos.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.desafiopicpaybackend.domain.Transacao;

/**
 * TransacaoViewDTO
 */
public record TransacaoViewDTO(String nomeRemetente, String nomeDestinatario, BigDecimal valor,
        LocalDateTime timestamp) {

    public TransacaoViewDTO(Transacao t) {
        this(t.getRemetente().getNome(), t.getDestinatario().getNome(), t.getValor(), t.getTimestamp());
    }

}

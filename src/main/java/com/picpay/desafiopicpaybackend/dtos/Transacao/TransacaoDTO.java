package com.picpay.desafiopicpaybackend.dtos.Transacao;

import java.math.BigDecimal;

public record TransacaoDTO(Long remetenteId, Long destinatarioId, BigDecimal valor) {

}

package com.picpay.desafiopicpaybackend.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(Long remetenteId, Long destinatarioId, BigDecimal valor) {

}

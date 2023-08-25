package com.picpay.desafiopicpaybackend.services;

import java.util.List;

import com.picpay.desafiopicpaybackend.domain.Transacao;
import com.picpay.desafiopicpaybackend.repositories.TransacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransacaoService
 */
@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> findTransacoesByRemetente(Long idRemetente) {
        return transacaoRepository.findAllTransacaoByRemetente_Id(idRemetente).orElseThrow();
    }

    public List<Transacao> findTransacoesByDestinatario(Long idDestinatario) {
        return transacaoRepository.findAllTransacaoByDestinatario_Id(idDestinatario).orElseThrow();
    }

    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }
}

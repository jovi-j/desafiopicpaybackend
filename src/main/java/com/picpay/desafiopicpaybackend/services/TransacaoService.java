package com.picpay.desafiopicpaybackend.services;

import java.util.List;

import com.picpay.desafiopicpaybackend.domain.Transacao;
import com.picpay.desafiopicpaybackend.domain.Usuario;
import com.picpay.desafiopicpaybackend.domain.enums.TipoUsuario;
import com.picpay.desafiopicpaybackend.dtos.TransacaoDTO;
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

    @Autowired
    private UsuarioService usuarioService;

    public List<Transacao> findTransacoesByRemetente(Long idRemetente) {
        return transacaoRepository.findAllTransacaoByRemetente_Id(idRemetente).orElseThrow();
    }

    public List<Transacao> findTransacoesByDestinatario(Long idDestinatario) {
        return transacaoRepository.findAllTransacaoByDestinatario_Id(idDestinatario).orElseThrow();
    }

    public Transacao salvarTransacao(TransacaoDTO dto) throws Exception {
        Transacao newTransacao = new Transacao();

        Usuario remetente = usuarioService.findById(dto.remetenteId());
        Usuario destinatario = usuarioService.findById(dto.destinatarioId());

        newTransacao.setRemetente(remetente);
        newTransacao.setDestinatario(destinatario);

        validarTransacao(newTransacao);

        transacaoRepository.save(newTransacao);

        return newTransacao;
    }

    public void validarTransacao(Transacao transacao) throws Exception {

        // RN 001: Usuário do tipo LOJISTA não pode realizar transferências, apenas
        // recebê-las.
        if (transacao.getRemetente().getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new Exception("Erro: O Usuário é do tipo LOJISTA.");
        }
        // RN 002: Usuário com saldo insuficiente não pode realizar transferência.
        if (transacao.getRemetente().getSaldo().compareTo(transacao.getValor()) < 0) {
            throw new Exception("Erro: Saldo insuficiente.");
        }
        // RN 003: Usuário não pode transferir para si mesmo.
        if (transacao.getRemetente().equals(transacao.getDestinatario())) {
            throw new Exception("Erro: O Usuário não pode realizar transferência para si mesmo.");
        }

    }
}

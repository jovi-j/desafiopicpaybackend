package com.picpay.desafiopicpaybackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.picpay.desafiopicpaybackend.domain.Transacao;
import com.picpay.desafiopicpaybackend.domain.Usuario;
import com.picpay.desafiopicpaybackend.domain.enums.TipoUsuario;
import com.picpay.desafiopicpaybackend.dtos.Transacao.*;
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

    public List<TransacaoViewDTO> findTransacoesByRemetente(Long idRemetente) {
        return transacaoListToTransacaoViewDTOList(
                transacaoRepository.findAllTransacaoByRemetente_Id(idRemetente).orElseThrow());
    }

    public List<TransacaoViewDTO> findTransacoesByDestinatario(Long idDestinatario) {
        return transacaoListToTransacaoViewDTOList(
                transacaoRepository.findAllTransacaoByDestinatario_Id(idDestinatario).orElseThrow());
    }

    public List<TransacaoViewDTO> findTransacoesByDestinatarioDoc(String documento) {
        Long idDestinatario = usuarioService.findByDocumento(documento).getId();
        Optional<List<Transacao>> listaOpt = transacaoRepository.findAllTransacaoByDestinatario_Id(idDestinatario);
        if (listaOpt.isPresent()) {
            return transacaoListToTransacaoViewDTOList(listaOpt.get());
        }

        return new ArrayList<TransacaoViewDTO>();
    }

    public TransacaoViewDTO salvarTransacao(TransacaoDTO dto) throws Exception {
        Transacao newTransacao = new Transacao();

        Usuario remetente = usuarioService.findById(dto.remetenteId());
        Usuario destinatario = usuarioService.findById(dto.destinatarioId());

        newTransacao.setRemetente(remetente);
        newTransacao.setDestinatario(destinatario);

        newTransacao.setValor(dto.valor());

        validarTransacao(newTransacao);

        usuarioService.subtrairSaldo(remetente, newTransacao.getValor());

        transacaoRepository.save(newTransacao);

        return new TransacaoViewDTO(newTransacao);
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

    public TransacaoViewDTO findTransacaoById(Long id) {
        Transacao t = transacaoRepository.findById(id).orElseThrow();
        return new TransacaoViewDTO(t);
    }

    private List<TransacaoViewDTO> transacaoListToTransacaoViewDTOList(List<Transacao> lista) {
        return lista.stream().map(TransacaoViewDTO::new).toList();
    }
}

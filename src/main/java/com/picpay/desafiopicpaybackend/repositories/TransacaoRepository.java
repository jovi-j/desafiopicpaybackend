package com.picpay.desafiopicpaybackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.picpay.desafiopicpaybackend.domain.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    public Optional<Transacao> findTransacaoById(Long id);

    public Optional<List<Transacao>> findAllTransacaoByRemetente_Id(Long idRemetente);

    public Optional<List<Transacao>> findAllTransacaoByDestinatario_Id(Long idDestinatario);

}

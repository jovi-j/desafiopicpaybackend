package com.picpay.desafiopicpaybackend.controllers;

import java.util.List;

import com.picpay.desafiopicpaybackend.dtos.Transacao.*;
import com.picpay.desafiopicpaybackend.services.TransacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/remetente/byDoc/{documento}/")
    public ResponseEntity<List<TransacaoViewDTO>> findTransacoesByRemetenteDoc(@PathVariable final String documento) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(transacaoService.findTransacoesByDestinatarioDoc(documento));
    }

    @GetMapping("/remetente/byId/{idRemetente}")
    public ResponseEntity<List<TransacaoViewDTO>> findTransacoesByRemetenteId(@PathVariable final Long idRemetente) {
        List<TransacaoViewDTO> lista = transacaoService.findTransacoesByRemetente(idRemetente);

        if (lista.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lista);

        return ResponseEntity.status(HttpStatus.FOUND).body(lista);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<TransacaoViewDTO> findTransacaoById(@PathVariable final Long id) {
        try {
            TransacaoViewDTO tr = transacaoService.findTransacaoById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(tr);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping
    public ResponseEntity<TransacaoViewDTO> criarTransacao(@RequestBody final TransacaoDTO transacao) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.salvarTransacao(transacao));

    }

}

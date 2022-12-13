package com.agropix.itau.service;


import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.ContaResponse;
import com.agropix.itau.mapper.ContaMapper;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.model.Conta;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository repository;
    private  final ContaMapper mapper;
    private final ClienteService clienteService;
    private final TipoContaService tipoContaService;

    public Conta save(ContaRequest contaRequest) {
        Conta conta = mapper.toModel(contaRequest);
        TipoConta tipoConta = tipoContaService.findTipoContaById(contaRequest.getTipoContaId());
        Cliente cliente = clienteService.findById(contaRequest.getClienteId());
        conta.setCliente(cliente);
        conta.setTipoConta(tipoConta);
        repository.save(conta);
        return conta;
    }

    public Conta findById(UUID contaId) {
        Conta conta = repository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        return conta;
    }

    public Conta update(UUID contaId, ContaRequest contaRequest) {
        Conta conta = findById(contaId);
        conta = mapper.toModel(contaRequest);
        conta.setId(contaId);
        repository.save(conta);
        return conta;
    }

    public void delete(UUID contaId) {
        Conta conta = findById(contaId);
        repository.delete(conta);
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Conta deposit(UUID contaId, BigDecimal valor) {
        Conta conta = findById(contaId);
        conta.deposito(valor);
        repository.save(conta);
        return conta;
    }

    public Conta withdraw(UUID contaId, BigDecimal valor) {
        Conta conta = findById(contaId);
        conta.saque(valor);
        repository.save(conta);
        return conta;
    }

}

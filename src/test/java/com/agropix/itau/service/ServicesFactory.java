package com.agropix.itau.service;

import com.agropix.itau.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServicesFactory {

    public static Cliente createCliente() {
        return Cliente.builder()
                .id(UUID.randomUUID())
                .nome("Thiago Felix")
                .cpf("329.626.500-74")
                .email("thiagofelixdasilva099@gmail.com")
                .telefone("+5592994568790")
                .build();
    }

    public static List<Cliente> createClienteList(Integer tamanhoLista) {
        final List<Cliente> clienteList = new ArrayList<>();
        for (int i = 0; i < tamanhoLista; i++) {
            clienteList.add(createCliente());
        }
        return clienteList;
    }

    public static TipoConta createTipoConta() {
        return TipoConta.builder()
                .id(UUID.randomUUID())
                .tipoConta("Corrente")
                .build();
    }

    public static List<TipoConta> createTipoContaList(Integer tamanhoLista) {
        final List<TipoConta> tipoContaList = new ArrayList<>();
        for (int i = 0; i < tamanhoLista; i++) {
            tipoContaList.add(createTipoConta());
        }
        return tipoContaList;
    }

    public static Conta createConta() {
        Conta conta = new Conta(UUID.randomUUID(), 554684L, 2, "Alvorada",
                createCliente(), createTipoConta(), "Itaú");
        return conta;
    }

    public static List<Conta> createContaList(Integer tamanhoLista) {
        final List<Conta> contaList = new ArrayList<>();
        for (int i = 0; i < tamanhoLista; i++) {
            contaList.add(createConta());
        }
        return contaList;
    }

    public static ChavePix createChavePix() {
        return ChavePix.builder()
                .id(UUID.randomUUID())
                .chavePix(UUID.randomUUID().toString())
                .tipo("ALEATORIA")
                .conta(createConta())
                .build();
    }


    public static List<ChavePix> createChavePixList(Integer tamanhoLista) {
        final List<ChavePix> chavePixList = new ArrayList<>();
        for (int i = 0; i < tamanhoLista; i++) {
            chavePixList.add(createChavePix());
        }
        return chavePixList;
    }

    public static TransferenciaPix createTransferenciaPix() {
        return TransferenciaPix.builder()
                .id(UUID.randomUUID())
                .chaveOrigem(createChavePix())
                .chaveDestino(UUID.randomUUID().toString())
                .statusTransferencia(StatusTransferencia.PENDENTE)
                .valor(100.00)
                .build();
    }

    public static List<TransferenciaPix> createTransferenciaPixList(Integer tamanhoLista) {
        final List<TransferenciaPix> transferenciaPixList = new ArrayList<>();
        for (int i = 0; i < tamanhoLista; i++) {
            transferenciaPixList.add(createTransferenciaPix());
        }
        return transferenciaPixList;
    }
}

package com.agropix.itau.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ContaTest {

    private Conta conta;

    @BeforeEach
    void setup() {
        Cliente cliente = new Cliente();
        TipoConta tipoConta = new TipoConta(UUID.randomUUID(), "Corrente");
        conta = new Conta(UUID.randomUUID(), 1515L, 2, "alvorada", cliente, tipoConta, "Itaú");
    }

    @Test
    public void deve_depositar_saldo_conta() {
        Double valorDeposito = 100.00;
        conta.deposito(valorDeposito);
        assertEquals(100, conta.getSaldo());
    }

    @Test
    public void deve_sacar_saldo_conta() {
        Double valorSaque = 50.00;
        conta.deposito(100.00);
        conta.saque(valorSaque);
        assertEquals(50, conta.getSaldo());
    }
}

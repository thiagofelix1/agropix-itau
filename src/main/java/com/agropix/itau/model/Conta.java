package com.agropix.itau.model;

import com.agropix.itau.exceptions.SaldoInsuficienteException;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@Entity
@Getter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    @NotNull
    private Long numeroConta;

    @Column
    @NotNull
    private Integer digito;

    @Column
    @NotNull
    private String agencia;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Cliente cliente;

    @Column
    @NotNull
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_conta_id")
    @NotNull
    private TipoConta tipoConta;

    @Column
    @NotNull
    private String nomeBanco = "ITAU";

    public Conta() {
        this.saldo = 0.0;
    }

    public Conta(UUID id, Long numeroConta, Integer digito, String agencia, Cliente cliente, TipoConta tipoConta) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.digito = digito;
        this.agencia = agencia;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
    }

    public void deposito(Double valor) {
        verificarValorNegativo(valor);
        saldo+=valor;
    }

    public void saque(Double valor) {
        verificarValorNegativo(valor);
        if (saldo < valor) {
            throw new SaldoInsuficienteException("valor socilitado de saque é maior que o saldo");
        }
        saldo -= valor;
    }

    private void verificarValorNegativo(Double valor) {
        if (valor <= 0) {
            throw new SaldoInsuficienteException("valor da operação não pode ser negativo ou zero.");
        }
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
}

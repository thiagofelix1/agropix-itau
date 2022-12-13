package com.agropix.itau.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Getter
public class Conta {

    public Conta() {
        this.saldo = new BigDecimal(0);
    }

    public Conta(UUID id, Long numeroConta, Integer digito, String agencia, Cliente cliente, TipoConta tipoConta, String nomeBanco) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.digito = digito;
        this.agencia = agencia;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.nomeBanco = nomeBanco;
        this.saldo = new BigDecimal(0);
    }

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
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_conta_id")
    @NotNull
    private TipoConta tipoConta;

    @Column
    @NotNull
    private String nomeBanco;

    public void deposito(BigDecimal valor) {
        saldo.add(valor);
    }

    public void saque(BigDecimal valor) {

        if (saldo.compareTo(valor) == -1) {
            throw new IllegalArgumentException("valor socilitado de saque é maior que o saldo");
        }
        saldo.subtract(valor);
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

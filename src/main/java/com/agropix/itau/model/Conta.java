package com.agropix.itau.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {

    //ToDo: Validações da Conta

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private Long numeroConta;

    @Column
    private Integer digito;

    @Column
    private String agencia;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_conta_id")
    private TipoConta tipoConta;

    @Column
    private String nomeBanco;
}

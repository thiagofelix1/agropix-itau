package com.agropix.itau.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_conta_id")
    @NotNull
    private TipoConta tipoConta;

    @Column
    @NotNull
    private String nomeBanco;

}

package com.agropix.itau.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaPix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chave_origem_id")
    private ChavePix chaveOrigem;

    @Column
    private String chaveDestino;

    @Column
    private Double valor;

    @Column
    private StatusTransferencia statusTransferencia;
}

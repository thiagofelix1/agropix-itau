package com.agropix.itau.repository;

import com.agropix.itau.model.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoContaRepository extends JpaRepository<TipoConta, UUID> {
}

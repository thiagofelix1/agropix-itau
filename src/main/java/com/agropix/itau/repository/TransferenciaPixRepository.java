package com.agropix.itau.repository;

import com.agropix.itau.dto.TransferenciaPixRequest;
import com.agropix.itau.model.TransferenciaPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix, UUID> {

}

package com.agropix.itau.repository;

import com.agropix.itau.dto.TransferenciaPixRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPixRequest, UUID> {

}

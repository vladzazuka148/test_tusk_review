package org.example.model.repository;

import org.example.model.entity.DigitalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DigitalServiceRepository extends JpaRepository<DigitalService, UUID> {
    boolean existsByName(String name);
}

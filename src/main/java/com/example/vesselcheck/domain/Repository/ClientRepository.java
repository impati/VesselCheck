package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}

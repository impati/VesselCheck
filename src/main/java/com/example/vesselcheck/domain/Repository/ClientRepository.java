package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByKakaoId(Long kakaoId);
}
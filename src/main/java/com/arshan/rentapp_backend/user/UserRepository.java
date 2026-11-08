package com.arshan.rentapp_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RentAppUser, Long > {
    Optional<RentAppUser> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}

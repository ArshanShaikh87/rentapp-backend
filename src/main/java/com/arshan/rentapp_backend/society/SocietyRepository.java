package com.arshan.rentapp_backend.society;

import com.arshan.rentapp_backend.society.dto.SocietyResponse;
import com.arshan.rentapp_backend.user.RentAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  SocietyRepository extends JpaRepository<Society, Long> {

    List<Society> findByOwnerAndIsActiveTrue(RentAppUser owner);

    Optional<Society> findByIdAndOwnerAndIsActiveTrue(long id, RentAppUser owner);


}

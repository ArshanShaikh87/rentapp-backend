package com.arshan.rentapp_backend.society;

import com.arshan.rentapp_backend.security.RentAppUserDetails;
import com.arshan.rentapp_backend.society.dto.CreateSocietyRequest;
import com.arshan.rentapp_backend.society.dto.DeleteSocietyResponse;
import com.arshan.rentapp_backend.society.dto.SocietyResponse;
import com.arshan.rentapp_backend.society.dto.UpdateSocietyRequest;
import com.arshan.rentapp_backend.user.RentAppUser;
import com.arshan.rentapp_backend.user.UserRole;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SocietyService {

    private final SocietyRepository societyRepository;

    public SocietyService(SocietyRepository societyRepository) {
        this.societyRepository = societyRepository;
    }

    // Endpoint : POST /api/v1/societies
    public SocietyResponse createSociety(
            CreateSocietyRequest createSocietyRequest) throws AccessDeniedException {

        RentAppUser user = getCurrentUser();

        Society society = new Society();
        society.setOwner(user);
        society.setName(createSocietyRequest.name());
        society.setAddress(createSocietyRequest.address());
        society.setCity(createSocietyRequest.city());
        society.setState(createSocietyRequest.state());
        society.setPincode(createSocietyRequest.pincode());
        society.setIsActive(true);

        Society savedSociety = societyRepository.save(society);

        return mapToSocietyResponse(savedSociety);
    }

    // Authentication method for Owner
    private RentAppUser getCurrentUser() throws AccessDeniedException {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null ||
                !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof RentAppUserDetails rentAppUserDetails)){
            throw new AccessDeniedException("User is not authenticated");
        }

        RentAppUser user = rentAppUserDetails.getUser();
        if (user.getRole() != UserRole.OWNER){
            throw new AccessDeniedException("Only owners can manage societies");
        }
        return user;
    }

    //Mapper method
    private SocietyResponse mapToSocietyResponse(Society society){
        return new SocietyResponse(
                society.getId(),
                society.getName(),
                society.getAddress(),
                society.getCity(),
                society.getState(),
                society.getPincode(),
                society.getIsActive()
        );
    }

    // Endpoint : GET /api/v1/societies
    public List<SocietyResponse> getAllSocieties(){
            RentAppUser user = getCurrentUser();
            List<Society> societies = societyRepository.findByOwnerAndIsActiveTrue(user);
            List<SocietyResponse> societyResp = societies.stream().map(
                    this :: mapToSocietyResponse).toList();
            return societyResp;
    }

    //Endpoint : GET /api/v1/societies/{id}
    public SocietyResponse getSociety(long id){
        RentAppUser user = getCurrentUser();
        Optional<Society> society = societyRepository.findByIdAndOwnerAndIsActiveTrue(id , user);
        if(society.isPresent()){
            return mapToSocietyResponse(society.get());
        }
        else {
            throw new ResourceNotFoundException("Society Not Found");
        }
    }

    //Endpoint : PUT /api/v1/societies/{id}
    public SocietyResponse updateSociety(long id , UpdateSocietyRequest updateSocietyRequest){
        RentAppUser user = getCurrentUser();
        Optional<Society> existingSociety = societyRepository.findByIdAndOwnerAndIsActiveTrue(id , user);
        if(existingSociety.isEmpty()){
            throw new ResourceNotFoundException("Society Not Found");
        }
        Society society = existingSociety.get();
        society.setName(updateSocietyRequest.name());
        society.setAddress(updateSocietyRequest.address());
        society.setCity(updateSocietyRequest.city());
        society.setState(updateSocietyRequest.state());
        society.setPincode(updateSocietyRequest.pincode());
        return  mapToSocietyResponse(societyRepository.save(society));
    }

    //EndPoint : PATCH  /api/v1/societies/{id}/deactivate
    public DeleteSocietyResponse softDeleteSociety(long id){
        RentAppUser user = getCurrentUser();
        Optional<Society> existingSociety = societyRepository.findByIdAndOwnerAndIsActiveTrue(id, user);
        if(existingSociety.isEmpty()){
            throw new ResourceNotFoundException("Society Not Found");
        }
        Society society = existingSociety.get();
        society.setIsActive(false);
        societyRepository.save(society);
        return new DeleteSocietyResponse("Society Deleted Successfully");
    }



}

package com.arshan.rentapp_backend.society;

import com.arshan.rentapp_backend.society.dto.CreateSocietyRequest;
import com.arshan.rentapp_backend.society.dto.DeleteSocietyResponse;
import com.arshan.rentapp_backend.society.dto.CreateSocietyResponse;
import com.arshan.rentapp_backend.society.dto.UpdateSocietyRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/societies")
public class SocietyController {

       private final SocietyService societyService;
        public SocietyController(
                SocietyService societyService) {
            this.societyService = societyService;
        }

        //Endpoint : Create Society
        @PostMapping
        public CreateSocietyResponse addSociety(
                @Valid @RequestBody CreateSocietyRequest createSocietyRequest) {
            CreateSocietyResponse response = societyService.createSociety(createSocietyRequest);
            return response;
        }

        //Endpoint : Get All Societies
        @GetMapping
        public List<CreateSocietyResponse> getSocieties() {
            return societyService.getAllSocieties();
        }

        //Endpoint : Get Society by id
        @GetMapping("/{id}")
        public CreateSocietyResponse getSociety(@PathVariable long id) {
           return societyService.getSociety(id);
        }

        //EndPoint : Update Society by id
        @PutMapping("/{id}")
        public CreateSocietyResponse updateSociety(
                @PathVariable long id,
                @Valid @RequestBody UpdateSocietyRequest updateSocietyRequest) {
            return societyService.updateSociety(id, updateSocietyRequest);
        }

        //EndPoint : Soft Delete(Patch) By id
        @PatchMapping("/{id}/deactivate")
        public DeleteSocietyResponse softDeleteSociety(@PathVariable long id){
            return societyService.softDeleteSociety(id);
        }

}

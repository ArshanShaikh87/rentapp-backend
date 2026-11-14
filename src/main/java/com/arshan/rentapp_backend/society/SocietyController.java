package com.arshan.rentapp_backend.society;

import com.arshan.rentapp_backend.society.dto.CreateSocietyRequest;
import com.arshan.rentapp_backend.society.dto.DeleteSocietyResponse;
import com.arshan.rentapp_backend.society.dto.SocietyResponse;
import com.arshan.rentapp_backend.society.dto.UpdateSocietyRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/societies")
public class SocietyController {

       private final SocietyService societyService;
        public SocietyController(
                SocietyService societyService) {
            this.societyService = societyService;
        }

        //Endpoint : Create Society
        @PostMapping
        public SocietyResponse addSociety(
                @Valid @RequestBody CreateSocietyRequest createSocietyRequest) {
            SocietyResponse response = societyService.createSociety(createSocietyRequest);
            return response;
        }

        //Endpoint : Get All Societies
        @GetMapping
        public List<SocietyResponse> getSocieties() {
            return societyService.getAllSocieties();
        }

        //Endpoint : Get Society by id
        @GetMapping("/{id}")
        public SocietyResponse getSociety(@PathVariable long id) {
           return societyService.getSociety(id);
        }

        //EndPoint : Update Society by id
        @PutMapping("/{id}")
        public SocietyResponse updateSociety(
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

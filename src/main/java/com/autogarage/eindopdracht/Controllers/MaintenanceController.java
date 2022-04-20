package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.MaintenanceDTO;
import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;
import com.autogarage.eindopdracht.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    // GET REQUESTS
    @GetMapping("/maintenances")
    ResponseEntity<Object> getAllMaintenances () {
        return new ResponseEntity<>(maintenanceService.findAllMaintenances(), HttpStatus.OK);
    }
    @GetMapping("/maintenances/{id}")
    ResponseEntity<Object> getMaintenanceById (@PathVariable Long id) {
        return new ResponseEntity<>(maintenanceService.findMaintenanceById(id), HttpStatus.OK);
    }


    // POST REQUESTS
    @PostMapping("/maintenances")
    ResponseEntity<Object> createMaintenance (@Valid @RequestBody MaintenanceDTO maintenanceDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            MaintenanceDTO newMaintenance = maintenanceService.createMaintenance(maintenanceDTO);
            return new ResponseEntity<>(newMaintenance, HttpStatus.CREATED);
        }

    }

    @PostMapping("/maintenances/{id}/add")
    ResponseEntity<Object> addItemToMaintenance (@RequestBody MaintenanceItemDTO maintenanceItemDTO, @PathVariable Long id) {
        MaintenanceItemDTO maintenanceDTO =  maintenanceService.addItemToMaintenance(maintenanceItemDTO, id);
        return new ResponseEntity<>(maintenanceDTO, HttpStatus.OK);
    }

    // DELETE REQUEST
    @DeleteMapping("/maintenances/{id}/items/{itemId}")
    ResponseEntity<Object> deleteItemFromMaintenance (@PathVariable Long id, @PathVariable Long itemId) {
        return new ResponseEntity<>(maintenanceService.deleteItemFromMaintenance(id, itemId), HttpStatus.OK);
    }

    // DELETE REQUEST
    @DeleteMapping("/maintenances/{id}")
    ResponseEntity<Object> deleteMaintenance (@PathVariable Long id) {
        return new ResponseEntity<>(maintenanceService.deleteMaintenance(id), HttpStatus.OK);
    }
}

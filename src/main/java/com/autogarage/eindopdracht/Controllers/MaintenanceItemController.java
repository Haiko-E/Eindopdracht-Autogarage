package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;
import com.autogarage.eindopdracht.Service.MaintenanceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MaintenanceItemController {

    @Autowired
    MaintenanceItemService maintenanceItemService;

    // GET REQUESTS
    @GetMapping("/maintenanceItems")
    ResponseEntity<Object> getAllMaintenanceItems () {
        return new ResponseEntity<>(maintenanceItemService.findAllMaintenanceItems(), HttpStatus.OK);
    }
    @GetMapping("/maintenanceItems/{id}")
    ResponseEntity<Object> getMaintenanceItemById (@PathVariable Long id) {
        return new ResponseEntity<>(maintenanceItemService.findMaintenanceItemById(id), HttpStatus.OK);
    }


    // POST REQUESTS
    @PostMapping("/maintenanceItems")
    ResponseEntity<Object> createMaintenanceItem (@Valid @RequestBody MaintenanceItemDTO maintenanceItemDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            MaintenanceItemDTO newMaintenanceItem = maintenanceItemService.createMaintenanceItem(maintenanceItemDTO);
            return new ResponseEntity<>(newMaintenanceItem, HttpStatus.CREATED);
        }

    }

    // DELETE REQUEST
    @DeleteMapping("/maintenanceItems/{id}")
    ResponseEntity<Object> deleteMaintenanceItem (@PathVariable Long id) {
        return new ResponseEntity<>(maintenanceItemService.deleteMaintenanceItem(id), HttpStatus.OK);
    }
}

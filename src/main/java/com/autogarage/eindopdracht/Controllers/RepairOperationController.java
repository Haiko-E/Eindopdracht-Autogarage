package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.RepairOperationDTO;
import com.autogarage.eindopdracht.Service.RepairOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RepairOperationController {

    @Autowired
    RepairOperationService repairOperationService;

    // GET REQUESTS
    @GetMapping("/repair-operations")
    ResponseEntity<Object> getAllRepairOperations() {
        return new ResponseEntity<>(repairOperationService.findAllRepairOperations(), HttpStatus.OK);
    }

    @GetMapping("/repair-operations/{id}")
    ResponseEntity<Object> getRepairOperationById(@PathVariable Long id) {
        return new ResponseEntity<>(repairOperationService.findRepairOperationById(id), HttpStatus.OK);
    }

    // POST REQUESTS
    @PostMapping("/repair-operations")
    ResponseEntity<Object> createRepairOperation(@Valid @RequestBody RepairOperationDTO repairOperationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            RepairOperationDTO newRepairOperation = repairOperationService.createRepairOperation(repairOperationDTO);
            return new ResponseEntity<>(newRepairOperation, HttpStatus.CREATED);
        }

    }

    // PUT REQUESTS
    @PutMapping("/repair-operations/{id}")
    ResponseEntity<Object> updateRepairOperation(@PathVariable Long id, @Valid @RequestBody RepairOperationDTO repairOperationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(repairOperationService.updateRepairOperation(repairOperationDTO, id), HttpStatus.OK);
        }

    }

    // DELETE REQUEST
    @DeleteMapping("/repair-operations/{id}")
    ResponseEntity<Object> deleteRepairOperation(@PathVariable Long id) {
        return new ResponseEntity<>(repairOperationService.deleteRepairOperation(id), HttpStatus.OK);
    }
}

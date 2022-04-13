package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.PartDTO;
import com.autogarage.eindopdracht.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PartController {

    @Autowired
    PartService partService;

    // GET REQUESTS
    @GetMapping("/parts")
    ResponseEntity<Object> getAllParts () {
        return new ResponseEntity<>(partService.findAllParts(), HttpStatus.OK);
    }
    @GetMapping("/parts/{id}")
    ResponseEntity<Object> getPartById (@PathVariable Long id) {
        return new ResponseEntity<>(partService.findPartById(id), HttpStatus.OK);
    }


    // POST REQUESTS
    @PostMapping("/parts")
    ResponseEntity<Object> createPart (@Valid @RequestBody PartDTO partDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            PartDTO newPart = partService.createPart(partDTO);
            return new ResponseEntity<>(newPart, HttpStatus.CREATED);
        }

    }

    // PUT REQUESTS
    @PutMapping("/parts/{id}")
    ResponseEntity<Object> updatePart (@PathVariable Long id, @Valid @RequestBody PartDTO partDTO , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(partService.updatePart(partDTO, id), HttpStatus.OK);
        }

    }

    // DELETE REQUEST
    @DeleteMapping("/parts/{id}")
    ResponseEntity<Object> deletePart (@PathVariable Long id) {
        return new ResponseEntity<>(partService.deletePart(id), HttpStatus.OK);
    }
}

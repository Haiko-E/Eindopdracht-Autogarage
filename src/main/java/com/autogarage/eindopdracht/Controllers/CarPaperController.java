package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.Service.CarPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class CarPaperController {
    @Autowired
    CarPaperService carPaperService;

    // CREATE
    @PostMapping(value = "/car-papers")
    ResponseEntity<Object> createCarPaper(@RequestBody MultipartFile file) {
        try {
            if (Objects.equals(file.getContentType(), "application/pdf")) {
                String message = carPaperService.createCarPaper(file);
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("please upload a pdf file", HttpStatus.BAD_REQUEST);
        } catch (IOException exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value  = "/car-papers/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<Object> findCarPaperById (@PathVariable Long id) {
        byte[] carPaper = carPaperService.findCarPaperById(id);
        return new ResponseEntity<>(carPaper, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(value  = "/car-papers/{id}")
    ResponseEntity<Object> updateCarPaper (@RequestBody MultipartFile file, @PathVariable Long id) {
        try {
            String message = carPaperService.updateCarPaper(file, id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IOException exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE
    @DeleteMapping("car-papers/{id}")
    ResponseEntity<Object> deleteCarPaper (@PathVariable Long id) {
        return new ResponseEntity<>(carPaperService.deleteCarPaper(id), HttpStatus.OK);
    }

}

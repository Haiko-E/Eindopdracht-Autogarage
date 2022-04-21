package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.CarDTO;
import com.autogarage.eindopdracht.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    // CREATE
    @PostMapping("/cars")
    ResponseEntity<Object> createCar(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            CarDTO newCar = carService.createCar(carDTO);
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        }

    }

    // READ
    @GetMapping("/cars")
    ResponseEntity<Object> findCars (@RequestParam(value = "licenseplate", required = false) String licensePlate) {
        // find all cars when there is no query for licenseplate
        if(licensePlate == null) {
                List<CarDTO> car = carService.findAllCars();
                return new ResponseEntity<>(car, HttpStatus.OK);
        }
        // find car by key "licenseplate"
        else {
            CarDTO car = carService.findCarByLicensePlate(licensePlate);
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @GetMapping("/cars/{id}")
    ResponseEntity<Object> findCarById (@PathVariable Long id) {
        CarDTO car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("cars/{id}")
    ResponseEntity<Object> updateCar (@PathVariable Long id, @Valid @RequestBody CarDTO carDTO , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(carService.updateCar(carDTO, id), HttpStatus.OK);
        }
    }

    // DELETE
    @DeleteMapping("cars/{id}")
    ResponseEntity<Object> deleteCar (@PathVariable Long id) {
        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }

}

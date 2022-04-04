package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.CustomerDTO;
import com.autogarage.eindopdracht.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // GET REQUESTS
    @GetMapping("/customers")
    ResponseEntity<Object> getAllCustomers () {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }
    @GetMapping("/customers/{id}")
    ResponseEntity<Object> getCustomerById (@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }
    @GetMapping("/customers/{id}/cars")
    ResponseEntity<Object> getCustomerCars (@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerCars(id), HttpStatus.OK);
    }

    // POST REQUESTS
    @PostMapping("/customers")
    ResponseEntity<Object> createCustomer (@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        }

    }

    // PUT REQUESTS
    @PutMapping("/customers/{id}")
    ResponseEntity<Object> updateCustomer (@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(customerService.updateCustomer(customerDTO, id), HttpStatus.OK);
        }

    }

    // DELETE REQUEST
    @DeleteMapping("/customers/{id}")
    ResponseEntity<Object> deleteCustomer (@PathVariable Long id) {
            return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }


}

package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.EmployeeDTO;
import com.autogarage.eindopdracht.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("employees")
    ResponseEntity<Object> createEmployee (@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            EmployeeDTO newEmployee = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        }
    }
    // GET
    @GetMapping("/employees")
    ResponseEntity<Object> getAllCustomers () {
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }
    @GetMapping("/employees/{id}")
    ResponseEntity<Object> getCustomerById (@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findByEmployeeId(id), HttpStatus.OK);
    }


    // PUT REQUESTS
    @PutMapping("/employees/{id}")
    ResponseEntity<Object> updateEmployee (@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO, id), HttpStatus.OK);
        }

    }

    // DELETE
    @DeleteMapping("employees/{id}")
    ResponseEntity<Object> deleteEmployee (@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}

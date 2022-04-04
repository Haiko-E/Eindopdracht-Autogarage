package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.AppointmentDTO;
import com.autogarage.eindopdracht.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    // CREATE
    @PostMapping("/appointments")
    ResponseEntity<Object> createAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            AppointmentDTO newAppointment = appointmentService.createAppointment(appointmentDTO);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        }

    }

    // READ
    @GetMapping("/appointments")
    ResponseEntity<Object> findAllAppointments() {
        List<AppointmentDTO> allAppointments =  appointmentService.findAllAppointments();
        return new ResponseEntity<>(allAppointments, HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    ResponseEntity<Object> findAppointmentById (@PathVariable Long id) {
        AppointmentDTO appointmentDTO = appointmentService.findAppointmentById(id);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("appointments/{id}")
    ResponseEntity<Object> updateAppointment (@PathVariable Long id, @Valid @RequestBody AppointmentDTO appointmentDTO , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(appointmentService.updateAppointment(appointmentDTO, id), HttpStatus.OK);
        }


    }

    // DELETE
    @DeleteMapping("appointments/{id}")
    ResponseEntity<Object> deleteAppointment (@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.deleteAppointment(id), HttpStatus.OK);
    }
}

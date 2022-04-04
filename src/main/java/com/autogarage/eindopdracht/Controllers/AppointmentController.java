package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.Model.Appointment;
import com.autogarage.eindopdracht.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    // CREATE
    @PostMapping("/appointments")
    ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()){
                sb.append(error);
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(appointment);
            Appointment newAppointment = appointmentService.createAppointment(appointment);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        }

    }

    @GetMapping("/appointments")
    ResponseEntity<Object> findAllAppointments() {
        List<Appointment> allAppointments =  appointmentService.findAllAppointments();
        return new ResponseEntity<>(allAppointments, HttpStatus.OK);
    }
}

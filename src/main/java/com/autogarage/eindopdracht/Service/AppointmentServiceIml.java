package com.autogarage.eindopdracht.Service;


import com.autogarage.eindopdracht.Model.Appointment;
import com.autogarage.eindopdracht.Repository.AppointmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceIml implements AppointmentService{

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    //CREATE
    @Override
    public Appointment createAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
        return appointment;
    }

    // READ
    @Override
    public List<Appointment> findAllAppointments() {

        return appointmentRepo.findAll();
    }
}

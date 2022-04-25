package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.AppointmentDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Appointment;
import com.autogarage.eindopdracht.Repository.AppointmentRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceIml implements AppointmentService{

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    //CREATE
    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment =  modelMapper.map(appointmentDTO, Appointment.class);
        Appointment newAppointment =  appointmentRepo.save(appointment);
        return modelMapper.map(newAppointment, AppointmentDTO.class);
    }

    // READ
    @Override
    @JsonIgnoreProperties({"car", "customer"})
    public List<AppointmentDTO> findAllAppointments() {
        List<Appointment> appointments =  appointmentRepo.findAll();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO1 = new AppointmentDTO();
            appointmentDTO1.setId(appointment.getId());
            appointmentDTO1.setDescription(appointment.getDescription());
            appointmentDTO1.setMaintenanceDetails(appointment.getMaintenanceDetails());
            appointmentDTO1.setDate(appointment.getDate());
            appointmentDTO1.setLastModifiedDate(appointment.getLastModifiedDate());
            appointmentDTO1.setCreationDate(appointment.getCreationDate());
            appointmentDTOS.add(appointmentDTO1);
        }
        return appointmentDTOS;
    }

    @Override
    public AppointmentDTO findAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("appointment not found"));
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setDescription(appointment.getDescription());
            appointmentDTO.setMaintenanceDetails(appointment.getMaintenanceDetails());
            appointmentDTO.setDate(appointment.getDate());
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setCreationDate(appointment.getCreationDate());
            appointmentDTO.setLastModifiedDate(appointment.getLastModifiedDate());
            return appointmentDTO;

    }

    // UPDATE
    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("appointment not found"));
        appointment.setMaintenanceDetails(appointmentDTO.getMaintenanceDetails());
        appointment.setDescription(appointmentDTO.getDescription());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setCustomer(appointmentDTO.getCustomer());
        appointment.setCar(appointmentDTO.getCar());
        appointmentRepo.save(appointment);
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    //DELETE
    @Override
    public AppointmentDTO deleteAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepo.findById(id);
        if(appointment.isPresent()){
            AppointmentDTO appointmentDTO = modelMapper.map(appointment.get(), AppointmentDTO.class);
            appointmentRepo.deleteById(id);
            return appointmentDTO;
        } else {
            throw new RecordNotFoundException("unable to find appointment");
        }

    }
}

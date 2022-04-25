package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointment);
    List<AppointmentDTO > findAllAppointments();
    AppointmentDTO findAppointmentById(Long id);
    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, Long id);
    AppointmentDTO deleteAppointment(Long id);
}

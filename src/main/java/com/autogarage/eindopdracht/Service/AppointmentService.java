package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.Model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
   List<Appointment > findAllAppointments ();
}

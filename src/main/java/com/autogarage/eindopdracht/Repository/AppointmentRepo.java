package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}

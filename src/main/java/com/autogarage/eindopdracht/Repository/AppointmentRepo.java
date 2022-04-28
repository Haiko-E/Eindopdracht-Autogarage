package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Modifying
    @Query("DELETE Appointment a WHERE a.car.id = ?1")
    void deleteByCarId(Long carId);

    @Modifying
    @Query("DELETE Appointment a WHERE a.customer.id = ?1")
    void deleteByCustomerId(Long customerId);
}

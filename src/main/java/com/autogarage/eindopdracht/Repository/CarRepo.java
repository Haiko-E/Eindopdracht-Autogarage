package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Optional<Car> findByLicensePlate (String licensePlate);
}

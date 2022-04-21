package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
    List<CarDTO> findAllCars ();
    CarDTO findCarByLicensePlate (String licensePlate);
    CarDTO findCarById(Long id);
    CarDTO updateCar(CarDTO carDTO, Long id);
    CarDTO deleteCar(Long id);
    String addCarPapersToCar(Long carId, MultipartFile carPapers) throws IOException;
}

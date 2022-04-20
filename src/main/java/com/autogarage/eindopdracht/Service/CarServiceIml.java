package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Repository.CarRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceIml implements CarService {
    @Autowired
    CarRepo carRepo;

    @Autowired
    private ModelMapper modelMapper;

    //CREATE
    @Override
    public CarDTO createCar(CarDTO carDTO) {
        Car newCar = modelMapper.map(carDTO, Car.class);
        Car car = carRepo.save(newCar);
        return modelMapper.map(car, CarDTO.class);
    }
     //READ
    @Override
    public List<CarDTO> findAllCars() {
        List<Car> cars = carRepo.findAll();
        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car : cars){
            CarDTO carDTO= modelMapper.map(car, CarDTO.class);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }

    @Override
    public CarDTO findCarByLicensePlate(String licensePlate) {
        Optional<Car> car = carRepo.findByLicensePlate(licensePlate);
        if (car.isPresent()) {
            Car car1 = car.get();
            return modelMapper.map(car1, CarDTO.class);
        } else {
            throw new RecordNotFoundException("Car not found");
        }
    }

    @Override
    public CarDTO findCarById(Long id) {
        Optional<Car> car = carRepo.findById(id);
        if (car.isPresent()) {
            Car car1 = car.get();
            return modelMapper.map(car1, CarDTO.class);
        } else {
            throw new RecordNotFoundException("Car not found");
        }
    }

    // UPDATE
    @Override
    public CarDTO updateCar(CarDTO carDTO, Long id) {
        Car car = carRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("Car not found"));
        car.setBrand(carDTO.getBrand());
        car.setType(carDTO.getType());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setCustomer(carDTO.getCustomer());
        carRepo.save(car);
        return modelMapper.map(car, CarDTO.class);
    }

    // DELETE
    @Override
    public CarDTO deleteCar(Long id) {
        Optional<Car> car = carRepo.findById(id);
        if(car.isPresent()){
            CarDTO carDTO = modelMapper.map(car.get(), CarDTO.class);
            carRepo.deleteById(id);
            return carDTO;
        } else {
            throw new RecordNotFoundException("unable to find car");
        }
    }
}

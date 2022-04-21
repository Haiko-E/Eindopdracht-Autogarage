package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarPaperDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.CarPaper;
import com.autogarage.eindopdracht.Repository.CarPaperRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CarPaperServiceIml implements CarPaperService {
    @Autowired
    CarPaperRepo carPaperRepo;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    @Override
    public CarPaper createCarPaper(MultipartFile file) throws IOException {
        CarPaper newCarPaper = new CarPaper();
        newCarPaper.setCarPapers(file.getBytes());
        CarPaper carPaper = carPaperRepo.save(newCarPaper);

        return carPaper;
    }

    // READ
    @Override
    public byte[] findCarPaperById(Long id) {
        Optional<CarPaper> carPaper = carPaperRepo.findById(id);
        if (carPaper.isPresent()) {
            return carPaper.get().getCarPapers();
        } else {
            throw new RecordNotFoundException("Car papers not found");
        }
    }

    // UPDATE
    @Override
    public String updateCarPaper(MultipartFile file, Long id) throws IOException {
        CarPaper carPaper = carPaperRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("CarPaper not found"));
        carPaper.setCarPapers(file.getBytes());
        carPaperRepo.save(carPaper);
        return "Car papers are updated";
    }

    // DELETE
    @Override
    public String deleteCarPaper(Long id) {
        CarPaper carPaper = carPaperRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("CarPaper not found"));
        carPaperRepo.deleteById(id);
        return "Car papers deleted";

    }
}

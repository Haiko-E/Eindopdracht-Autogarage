package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.Model.CarPaper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarPaperService {
    CarPaper createCarPaper(MultipartFile file) throws IOException;
    byte[] findCarPaperById(Long id);
    String updateCarPaper(MultipartFile file, Long id) throws IOException;
    String deleteCarPaper(Long id);
}

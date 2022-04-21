package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.CarPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPaperRepo extends JpaRepository<CarPaper, Long> {

}

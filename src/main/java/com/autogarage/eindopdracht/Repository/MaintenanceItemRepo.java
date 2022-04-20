package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.MaintenanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceItemRepo extends JpaRepository<MaintenanceItem, Long> {
    List<MaintenanceItem> findByMaintenance(Long id);
}

package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {
}

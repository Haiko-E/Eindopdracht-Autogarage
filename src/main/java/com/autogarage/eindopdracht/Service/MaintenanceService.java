package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.MaintenanceDTO;
import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;

import java.util.List;

public interface MaintenanceService {
    MaintenanceDTO createMaintenance(MaintenanceDTO maintenanceDTO);
    MaintenanceItemDTO addItemToMaintenance(MaintenanceItemDTO maintenanceItemDTO, Long maintenanceId);
    MaintenanceItemDTO deleteItemFromMaintenance(Long maintenanceId, Long ItemId);
    List<MaintenanceDTO> findAllMaintenances();
    MaintenanceDTO findMaintenanceById (Long id);
    MaintenanceDTO deleteMaintenance(Long id);
}

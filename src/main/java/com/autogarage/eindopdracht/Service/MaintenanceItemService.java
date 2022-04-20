package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;

import java.util.List;

public interface MaintenanceItemService {
    MaintenanceItemDTO createMaintenanceItem(MaintenanceItemDTO maintenanceItemDTO);
    List<MaintenanceItemDTO> findAllMaintenanceItems();
    MaintenanceItemDTO findMaintenanceItemById (Long id);
    MaintenanceItemDTO deleteMaintenanceItem(Long id);
    void deleteItemsByMaintenanceId (Long maintenanceId);
}

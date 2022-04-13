package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.RepairOperationDTO;

import java.util.List;

public interface RepairOperationService {
    RepairOperationDTO createRepairOperation(RepairOperationDTO repairOperationDTO);
    List<RepairOperationDTO> findAllRepairOperations();
    RepairOperationDTO findRepairOperationById (Long id);
    RepairOperationDTO updateRepairOperation (RepairOperationDTO repairOperationDTO, Long id);
    RepairOperationDTO deleteRepairOperation(Long id);
}

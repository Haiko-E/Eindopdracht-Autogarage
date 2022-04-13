package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.RepairOperationDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.RepairOperation;
import com.autogarage.eindopdracht.Repository.RepairOperationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepairOperationServiceIml implements RepairOperationService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RepairOperationRepo repairOperationRepo;

    // CREATE
    @Override
    public RepairOperationDTO createRepairOperation(@Valid RepairOperationDTO repairOperationDTO) {
        RepairOperation newRepairOperation = modelMapper.map(repairOperationDTO, RepairOperation.class);
        RepairOperation savedRepairOperation = repairOperationRepo.save(newRepairOperation);
        return modelMapper.map(savedRepairOperation, RepairOperationDTO.class);
    }

    // READ
    @Override
    public List<RepairOperationDTO> findAllRepairOperations() {
        List<RepairOperation> repairOperations =  repairOperationRepo.findAll();
        List<RepairOperationDTO> repairOperationDTOS = new ArrayList<>();

        for(RepairOperation repairOperation : repairOperations) {
            RepairOperationDTO repairOperationDTO = modelMapper.map(repairOperation, RepairOperationDTO.class);
            repairOperationDTOS.add(repairOperationDTO);
        }
        return repairOperationDTOS;
    }

    @Override
    public RepairOperationDTO findRepairOperationById(Long id) {
        Optional<RepairOperation> repairOperation =  repairOperationRepo.findById(id);
        if (repairOperation.isPresent()){
            RepairOperationDTO repairOperationDTO = modelMapper.map(repairOperation.get(), RepairOperationDTO.class);
            return repairOperationDTO;
        } else {
            throw new RecordNotFoundException("repairOperation not found");
        }
    }

    // UPDATE
    @Override
    public RepairOperationDTO updateRepairOperation(RepairOperationDTO repairOperationDTO, Long id) {
        RepairOperation repairOperation = repairOperationRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("repairOperation not found"));
        repairOperation.setName(repairOperationDTO.getName());
        repairOperation.setPrice(repairOperationDTO.getPrice());
        repairOperationRepo.save(repairOperation);
        return modelMapper.map(repairOperation, RepairOperationDTO.class);
    }

    // DELETE
    @Override
    public RepairOperationDTO deleteRepairOperation(Long id) {
        Optional<RepairOperation> repairOperation = repairOperationRepo.findById(id);
        if(repairOperation.isPresent()){
            RepairOperationDTO repairOperationDTO = modelMapper.map(repairOperation.get(), RepairOperationDTO.class);
            repairOperationRepo.deleteById(id);
            return repairOperationDTO;
        } else {
            throw new RecordNotFoundException("unable to find repairOperation");
        }
    }
}

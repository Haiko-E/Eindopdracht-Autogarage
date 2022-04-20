package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;

import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.MaintenanceItem;

import com.autogarage.eindopdracht.Repository.MaintenanceItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceItemServiceIml implements MaintenanceItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MaintenanceItemRepo maintenanceItemRepo;

    @Override
    public MaintenanceItemDTO createMaintenanceItem(@Valid MaintenanceItemDTO maintenanceItemDTO) {
        if(maintenanceItemDTO.getPart() != null && maintenanceItemDTO.getRepairOperation() != null) {
            throw new RecordNotFoundException("you can only have 1 repair operation or 1 maintenanceItem in a maintenance item");
        }
        MaintenanceItem newMaintenanceItem = modelMapper.map(maintenanceItemDTO, MaintenanceItem.class);
        MaintenanceItem savedMaintenanceItem = maintenanceItemRepo.save(newMaintenanceItem);
        return modelMapper.map(savedMaintenanceItem, MaintenanceItemDTO.class);
    }

    @Override
    public List<MaintenanceItemDTO> findAllMaintenanceItems() {
            List<MaintenanceItem> maintenanceItems =  maintenanceItemRepo.findAll();
            List<MaintenanceItemDTO> maintenanceItemDTOS = new ArrayList<>();

            for(MaintenanceItem maintenanceItem : maintenanceItems) {
                MaintenanceItemDTO maintenanceItemDTO = modelMapper.map(maintenanceItem, MaintenanceItemDTO.class);
                maintenanceItemDTOS.add(maintenanceItemDTO);
            }
            return maintenanceItemDTOS;


    }

    @Override
    public MaintenanceItemDTO findMaintenanceItemById(Long id) {
        Optional<MaintenanceItem> maintenanceItem =  maintenanceItemRepo.findById(id);
        if (maintenanceItem.isPresent()){
            MaintenanceItemDTO maintenanceItemDTO = modelMapper.map(maintenanceItem.get(), MaintenanceItemDTO.class);
            return maintenanceItemDTO;
        } else {
            throw new RecordNotFoundException("maintenance item not found");
        }
    }


    @Override
    public MaintenanceItemDTO deleteMaintenanceItem(Long id) {
        Optional<MaintenanceItem> maintenanceItem = maintenanceItemRepo.findById(id);
        if(maintenanceItem.isPresent()){
            MaintenanceItemDTO maintenanceItemDTO = modelMapper.map(maintenanceItem.get(), MaintenanceItemDTO.class);
            maintenanceItemRepo.deleteById(id);
            return maintenanceItemDTO;
        } else {
            throw new RecordNotFoundException("unable to find maintenanceItem");
        }
    }

    @Override
    public void deleteItemsByMaintenanceId(Long maintenanceId) {
        List<MaintenanceItem> maintenanceItems =  maintenanceItemRepo.findAll();
        for (MaintenanceItem item : maintenanceItems) {
            if ( item.getMaintenance().getId() == maintenanceId) {
                maintenanceItemRepo.delete(item);
            }
        }

    }
}
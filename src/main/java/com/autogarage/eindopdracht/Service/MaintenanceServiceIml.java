package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.MaintenanceDTO;
import com.autogarage.eindopdracht.DTO.MaintenanceItemDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Maintenance;
import com.autogarage.eindopdracht.Model.MaintenanceItem;
import com.autogarage.eindopdracht.Repository.MaintenanceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceIml implements MaintenanceService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MaintenanceRepo maintenanceRepo;

    @Autowired
    MaintenanceItemService maintenanceItemService;

    @Autowired
    CarService carService;

    @Override
    public MaintenanceDTO createMaintenance(@Valid MaintenanceDTO maintenanceDTO) {
        // Check if car exists
        Long carID = maintenanceDTO.getCar().getId();
        carService.findCarById(carID);

        // if soo, create maintenance item
        Maintenance newMaintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        Maintenance savedMaintenance = maintenanceRepo.save(newMaintenance);
        return modelMapper.map(savedMaintenance, MaintenanceDTO.class);
    }

    @Override
    public MaintenanceItemDTO addItemToMaintenance(MaintenanceItemDTO maintenanceItemDTO, Long maintenanceId) {
        // Check if maintenanceID exists
        Optional<Maintenance> maintenance = maintenanceRepo.findById(maintenanceId);

        // If soo, execute this
        if(maintenance.isPresent()){
            maintenanceItemDTO.setMaintenance(maintenance.get());
            MaintenanceItemDTO itemDTO =  maintenanceItemService.createMaintenanceItem(maintenanceItemDTO);
            Maintenance maintenance1 = maintenance.get();
            List<MaintenanceItem> maintenanceItems =  maintenance1.getMaintenanceItems();
            MaintenanceItem maintenanceItem = modelMapper.map(itemDTO, MaintenanceItem.class);
            maintenanceItems.add(0, maintenanceItem);

            maintenance1.setMaintenanceItems(maintenanceItems);
            maintenanceRepo.save(maintenance1);
            return itemDTO;

        // else throw this error
        } else {
            throw new RecordNotFoundException("unable to find Maintenance");
        }

    }

        @Override
        public List<MaintenanceDTO> findAllMaintenances() {
            List<Maintenance> maintenances =  maintenanceRepo.findAll();
            List<MaintenanceDTO> maintenanceDTOS = new ArrayList<>();

            for(Maintenance maintenance : maintenances) {
                MaintenanceDTO maintenanceDTO = modelMapper.map(maintenance, MaintenanceDTO.class);
                maintenanceDTOS.add(maintenanceDTO);
            }

            return maintenanceDTOS;
    }

    @Override
    public MaintenanceDTO findMaintenanceById(Long id) {
        Optional<Maintenance> maintenance =  maintenanceRepo.findById(id);
        if (maintenance.isPresent()){
            MaintenanceDTO maintenanceDTO = modelMapper.map(maintenance.get(), MaintenanceDTO.class);
            return maintenanceDTO;
        } else {
            throw new RecordNotFoundException("maintenance item not found");
        }
    }

    @Override
    public MaintenanceItemDTO deleteItemFromMaintenance(Long maintenanceId, Long itemId) {
        Optional<Maintenance> maintenance =  maintenanceRepo.findById(maintenanceId);
        Boolean itemIdExistsInMaintenance = false;

        if (maintenance.isPresent()) {
            for (MaintenanceItem item : maintenance.get().getMaintenanceItems()) {
                if (item.getId() == itemId) {
                    itemIdExistsInMaintenance = true;
                }
            }

        }
        if(itemIdExistsInMaintenance) {
          return  maintenanceItemService.deleteMaintenanceItem(itemId);
        } else {
            throw new RecordNotFoundException("Item doesn't exist in this maintenance");
        }

    }


    @Override
    public MaintenanceDTO deleteMaintenance(Long id) {
        Optional<Maintenance> maintenance = maintenanceRepo.findById(id);
        if(maintenance.isPresent()){
            MaintenanceDTO maintenanceDTO = modelMapper.map(maintenance.get(), MaintenanceDTO.class);
            maintenanceItemService.deleteItemsByMaintenanceId(id);
            maintenanceRepo.deleteById(id);
            return maintenanceDTO;
        } else {
            throw new RecordNotFoundException("unable to find maintenance");
        }
    }
}
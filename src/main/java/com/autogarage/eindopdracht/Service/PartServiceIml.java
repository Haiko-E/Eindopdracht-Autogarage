package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.PartDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Part;
import com.autogarage.eindopdracht.Repository.PartRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartServiceIml implements PartService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PartRepo partRepo;

    // CREATE
    @Override
    public PartDTO createPart(@Valid PartDTO partDTO) {
        Part newPart = modelMapper.map(partDTO, Part.class);
        Part savedPart = partRepo.save(newPart);
        return modelMapper.map(savedPart, PartDTO.class);
    }

    // READ
    @Override
    public List<PartDTO> findAllParts() {
        List<Part> parts =  partRepo.findAll();
        List<PartDTO> partDTOS = new ArrayList<>();

        for(Part part : parts) {
            PartDTO partDTO = modelMapper.map(part, PartDTO.class);
            partDTOS.add(partDTO);
        }
        return partDTOS;
    }

    @Override
    public PartDTO findPartById(Long id) {
        Optional<Part> part =  partRepo.findById(id);
        if (part.isPresent()){
            PartDTO partDTO = modelMapper.map(part.get(), PartDTO.class);
            return partDTO;
        } else {
            throw new RecordNotFoundException("part not found");
        }
    }

    // UPDATE
    @Override
    public PartDTO updatePart(PartDTO partDTO, Long id) {
        Part part = partRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("part not found"));
        part.setName(partDTO.getName());
        part.setPrice(partDTO.getPrice());
        part.setInStock(partDTO.getInStock());
        partRepo.save(part);
        return modelMapper.map(part, PartDTO.class);
    }

    // DELETE
    @Override
    public PartDTO deletePart(Long id) {
        Optional<Part> part = partRepo.findById(id);
        if(part.isPresent()){
            PartDTO partDTO = modelMapper.map(part.get(), PartDTO.class);
            partRepo.deleteById(id);
            return partDTO;
        } else {
            throw new RecordNotFoundException("unable to find part");
        }
    }
}

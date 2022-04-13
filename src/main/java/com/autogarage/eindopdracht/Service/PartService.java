package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.PartDTO;

import java.util.List;

public interface PartService {
    PartDTO createPart(PartDTO partDTO);
    List<PartDTO> findAllParts();
    PartDTO findPartById (Long id);
    PartDTO updatePart (PartDTO partDTO, Long id);
    PartDTO deletePart(Long id);
}

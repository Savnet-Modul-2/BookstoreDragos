package com.example.SpringBookstore.service;

import com.example.SpringBookstore.entities.Exemplary;
import com.example.SpringBookstore.entitiesDTO.ExemplarsCreateDTO;
import com.example.SpringBookstore.entitiesDTO.ExemplarsResponseDTO;
import com.example.SpringBookstore.mapper.ExemplaryMapper;
import com.example.SpringBookstore.repositories.ExemplaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaryService {
    @Autowired
    private ExemplaryRepository exemplaryRepository;

    public ExemplarsResponseDTO createMultiple(ExemplarsCreateDTO exemplarsCreateDTO) {
        List<Exemplary> exemplarsToCreate = ExemplaryMapper.exemplarsCreateDTO2Exemplars(exemplarsCreateDTO);

        exemplarsToCreate.forEach(exemplary -> exemplaryRepository.save(exemplary));

        return new ExemplarsResponseDTO(ExemplaryMapper.exemplary2ExemplaryDTO(exemplarsToCreate.get(0)), exemplarsToCreate.size());
    }

    public Page<Exemplary> listPaginated(Integer pageNumber, Integer numberOfElements) {
        if (pageNumber != null && numberOfElements != null) {
            Pageable pageable = PageRequest.of(pageNumber, numberOfElements);
            return exemplaryRepository.findAll(pageable);
        }

        return exemplaryRepository.findAll(Pageable.unpaged());
    }

    public void delete(Long exemplaryID) {
        if (exemplaryRepository.existsById(exemplaryID)) exemplaryRepository.deleteById(exemplaryID);
        else throw new EntityNotFoundException("Exemplary with ID " + exemplaryID + " not found.");
    }
}

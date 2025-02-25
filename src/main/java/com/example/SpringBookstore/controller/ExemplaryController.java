package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entities.Exemplary;
import com.example.SpringBookstore.entitiesDTO.ExemplarsCreateDTO;
import com.example.SpringBookstore.entitiesDTO.ExemplarsResponseDTO;
import com.example.SpringBookstore.entitiesDTO.ExemplaryDTO;
import com.example.SpringBookstore.mapper.ExemplaryMapper;
import com.example.SpringBookstore.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/exemplars")
public class ExemplaryController {
    @Autowired
    private ExemplaryService exemplaryService;

    @PostMapping
    public ResponseEntity<?> createMultiple(@RequestBody ExemplarsCreateDTO exemplarsCreateDTO) {
        ExemplarsResponseDTO createdExemplars = exemplaryService.createMultiple(exemplarsCreateDTO);
        return ResponseEntity.ok().body(createdExemplars);
    }

    @GetMapping
    public ResponseEntity<?> listPaginated(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer numberOfElements) {
        Page<Exemplary> exemplars = exemplaryService.listPaginated(pageNumber, numberOfElements);

        List<ExemplaryDTO> exemplaryDTOS = exemplars.stream()
                .map(ExemplaryMapper::exemplary2ExemplaryDTO)
                .toList();

        return ResponseEntity.ok(exemplaryDTOS);
    }

    @DeleteMapping(path = "/{exemplaryID}")
    public ResponseEntity<?> delete(@PathVariable(name = "exemplaryID") Long exemplaryID) {
        exemplaryService.delete(exemplaryID);
        return ResponseEntity.noContent().build();
    }
}

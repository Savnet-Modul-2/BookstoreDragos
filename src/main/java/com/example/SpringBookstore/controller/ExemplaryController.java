package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entities.Exemplary;
import com.example.SpringBookstore.entitiesDTO.ExemplarsCreateDTO;
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
    private final ExemplaryService exemplaryService;

    @Autowired
    public ExemplaryController(ExemplaryService exemplaryService) {
        this.exemplaryService = exemplaryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExemplarsCreateDTO exemplarsCreateDTO) {
        List<Exemplary> exemplarsToCreate = ExemplaryMapper.exemplarsCreateDTO2Exemplars(exemplarsCreateDTO);
        List<Exemplary> createdExemplars = exemplaryService.create(exemplarsToCreate, exemplarsCreateDTO.getBookID());

        return ResponseEntity.ok(createdExemplars.stream()
                .map(ExemplaryMapper::exemplary2ExemplaryDTO)
                .toList());
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

package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Exemplary;
import com.example.SpringBookstore.entitiesDTO.ExemplarsCreateDTO;
import com.example.SpringBookstore.entitiesDTO.ExemplaryDTO;
import com.example.SpringBookstore.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExemplaryMapper {
    private static BookRepository bookRepository;

    @Autowired
    public ExemplaryMapper(BookRepository bookRepository) {
        ExemplaryMapper.bookRepository = bookRepository;
    }

    public static Exemplary exemplaryDTO2Exemplary(ExemplaryDTO exemplaryDTO) {
        Exemplary exemplary = new Exemplary();

        exemplary.setId(exemplaryDTO.getId());
        exemplary.setPublisher(exemplaryDTO.getPublisher());
        exemplary.setMaximumBookingTime(exemplaryDTO.getMaximumBookingTime());
        exemplary.setBook(BookMapper.bookDTO2Book(exemplaryDTO.getBookDTO()));

        return exemplary;
    }

    public static ExemplaryDTO exemplary2ExemplaryDTO(Exemplary exemplary) {
        ExemplaryDTO exemplaryDTO = new ExemplaryDTO();

        exemplaryDTO.setId(exemplary.getId());
        exemplaryDTO.setPublisher(exemplary.getPublisher());
        exemplaryDTO.setMaximumBookingTime(exemplary.getMaximumBookingTime());
        exemplaryDTO.setBookDTO(BookMapper.book2BookDTO(exemplary.getBook()));

        return exemplaryDTO;
    }

    public static List<Exemplary> exemplarsCreateDTO2Exemplars(ExemplarsCreateDTO exemplarsCreateDTO) {
        List<Exemplary> exemplars = new ArrayList<>();

        for (int i = 0; i < exemplarsCreateDTO.getNumberOfExemplars(); i++) {
            Exemplary exemplary = new Exemplary();

            Book book = bookRepository.findById(exemplarsCreateDTO.getBookID())
                    .orElseThrow(() -> new EntityNotFoundException("Book with ID " + exemplarsCreateDTO.getBookID() + " not found."));

            exemplary.setBook(book);
            exemplary.setPublisher(exemplarsCreateDTO.getPublisher());
            exemplary.setMaximumBookingTime(exemplarsCreateDTO.getMaximumBookingTime());

            exemplars.add(exemplary);
        }

        return exemplars;
    }
}

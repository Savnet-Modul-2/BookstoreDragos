package com.example.SpringBookstore.entitiesDTO;

import com.example.SpringBookstore.ReservationStatus;
import com.example.SpringBookstore.entitiesDTO.validation.AdvancedInformation;
import com.example.SpringBookstore.entitiesDTO.validation.ValidDateInTheFuture;
import com.example.SpringBookstore.entitiesDTO.validation.ValidDateOrder;

import java.time.LocalDate;

@ValidDateOrder(groups = AdvancedInformation.class)
@ValidDateInTheFuture(groups = AdvancedInformation.class)
public class ReservationDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus reservationStatus;
    private ExemplaryDTO exemplaryDTO;
    private UserDTO userDTO;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getStatus() {
        return reservationStatus;
    }

    public void setStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public ExemplaryDTO getExemplaryDTO() {
        return exemplaryDTO;
    }

    public void setExemplaryDTO(ExemplaryDTO exemplaryDTO) {
        this.exemplaryDTO = exemplaryDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}

package com.example.SpringBookstore.entityDTO;

import com.example.SpringBookstore.ReservationStatus;

import java.time.LocalDate;
import java.util.List;

public record ReservationFiltersDTO(LocalDate startDate, LocalDate endDate,
                                    List<ReservationStatus> reservationStatusFilters) {
}

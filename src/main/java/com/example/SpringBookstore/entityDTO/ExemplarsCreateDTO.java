package com.example.SpringBookstore.entityDTO;

import com.example.SpringBookstore.entityDTO.validation.AdvancedInformation;
import com.example.SpringBookstore.entityDTO.validation.ValidExemplarsCreateBookingTime;
import com.example.SpringBookstore.entityDTO.validation.ValidExemplarsCreateNumber;
import com.example.SpringBookstore.entityDTO.validation.ValidExemplarsCreatePublisher;

@ValidExemplarsCreatePublisher(groups = AdvancedInformation.class)
@ValidExemplarsCreateBookingTime(groups = AdvancedInformation.class)
@ValidExemplarsCreateNumber(groups = AdvancedInformation.class)
public class ExemplarsCreateDTO {
    private String publisher;
    private Integer maximumBookingTime;
    private Integer numberOfExemplars;
    private Long bookID;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getMaximumBookingTime() {
        return maximumBookingTime;
    }

    public void setMaximumBookingTime(Integer maximumBookingTime) {
        this.maximumBookingTime = maximumBookingTime;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Integer getNumberOfExemplars() {
        return numberOfExemplars;
    }

    public void setNumberOfExemplars(Integer numberOfExemplars) {
        this.numberOfExemplars = numberOfExemplars;
    }
}

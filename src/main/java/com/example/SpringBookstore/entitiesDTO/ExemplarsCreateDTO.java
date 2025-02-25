package com.example.SpringBookstore.entitiesDTO;

public class ExemplarsCreateDTO {
    private Long bookID;
    private String publisher;
    private Integer maximumBookingTime;
    private Integer numberOfExemplars;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

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

    public Integer getNumberOfExemplars() {
        return numberOfExemplars;
    }

    public void setNumberOfExemplars(Integer numberOfExemplars) {
        this.numberOfExemplars = numberOfExemplars;
    }
}

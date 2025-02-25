package com.example.SpringBookstore.entitiesDTO;

public class ExemplarsResponseDTO {
    private ExemplaryDTO exemplaryDTO;
    private Integer numberOfExemplarsCreated;

    public ExemplarsResponseDTO(ExemplaryDTO exemplaryDTO, Integer numberOfExemplarsCreated) {
        this.exemplaryDTO = exemplaryDTO;
        this.numberOfExemplarsCreated = numberOfExemplarsCreated;
    }

    public ExemplaryDTO getExemplaryDTO() {
        return exemplaryDTO;
    }

    public void setExemplaryDTO(ExemplaryDTO exemplaryDTO) {
        this.exemplaryDTO = exemplaryDTO;
    }

    public Integer getNumberOfExemplarsCreated() {
        return numberOfExemplarsCreated;
    }

    public void setNumberOfExemplarsCreated(Integer numberOfExemplarsCreated) {
        this.numberOfExemplarsCreated = numberOfExemplarsCreated;
    }
}

package com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses;

import com.etiya.rentACar.entities.AdditionalService;
import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAdditionalServiceDetailDto {
    private int id;
    private int rentalId;
    private int additionalServiceId;
}

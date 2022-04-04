package com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses;

import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalAdditionalServiceDetailDto {
    private int id;
    private int rentalId;
    private int additionalServiceId;
}

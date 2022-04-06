package com.etiya.rentACar.business.responses.paymentResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private double totalPrice;
    private String customerFirstName;
    private String customerLastName;
    private String rentalCarBrandName;
}

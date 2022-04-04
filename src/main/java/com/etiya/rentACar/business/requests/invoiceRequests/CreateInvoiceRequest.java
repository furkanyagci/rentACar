package com.etiya.rentACar.business.requests.invoiceRequests;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

    private String invoiceNumber;

    private LocalDate createDate;

    @JsonIgnore
    private LocalDate rentDate;

    @JsonIgnore
    private LocalDate returnDate;

    @JsonIgnore
    private int dayCount;

    private double totalPrice;

    private int customerId;
}

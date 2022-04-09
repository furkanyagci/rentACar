package com.etiya.rentACar.business.requests.paymentRequests;

import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.CreateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    private String creditCardNumber;
    private String ownerFirstName;
    private String ownerLastName;
    private String deadLine;
    private String CVVCode;
    @JsonIgnore
    private double totalPrice;

    private int customerId;

    //Creata rental
    private LocalDate rentDate;

    private LocalDate returnDate;

    @JsonIgnore
    private double dailyPrice;

    private double kilometer;

    private int carId;

    //private int customerId;

    private int rentCityId;

    private int returnedCityId;

    //CreateRentalAdditionalServiceDetailRequest
    private int rentalId;
    private List<Integer> additionalServiceId;

    //
    @JsonIgnore
    private int invoiceId;

    private String invoiceNumber;

    private LocalDate createDate;

    //private LocalDate rentDate;

    //private LocalDate returnDate;


    private int dayCount;

    //private double totalPrice;

    //private int customerId;
}

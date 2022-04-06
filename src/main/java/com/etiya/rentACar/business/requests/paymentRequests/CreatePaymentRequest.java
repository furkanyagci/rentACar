package com.etiya.rentACar.business.requests.paymentRequests;

import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.CreateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double totalPrice;

    private int customerId;

    private CreateRentalRequest createRentalRequest;
    List<CreateRentalAdditionalServiceDetailRequest> createRentalAdditionalServiceDetailRequests;
    private CreateInvoiceRequest createInvoiceRequest;
}

package com.etiya.rentACar.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPosServiceInfo {
    //kredikratı numarası, sahibi, son tarih, cvv değeri
    private String creditCardNumber;
    private String ownerFirstName;
    private String ownerLastName;
    private String deadLine;
    private String CVVCode;
    private double totalPrice;
}

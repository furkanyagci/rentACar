package com.etiya.rentACar.adapter;

import com.etiya.rentACar.tr.IsBankService.IsBankPosService;
import org.springframework.stereotype.Service;

@Service
public class IsBankServiceAdapter implements PaymentCheckPosService{

    @Override
    public boolean Pay(PaymentPosServiceInfo paymentPosServiceInfo) {
        IsBankPosService isBankPosService = new IsBankPosService();
        //kredikratı numarası, sahibi, son tarih, cvv değeri
        return isBankPosService.pay(paymentPosServiceInfo.getCreditCardNumber()
        , paymentPosServiceInfo.getOwnerFirstName(),paymentPosServiceInfo.getOwnerLastName(), paymentPosServiceInfo.getDeadLine()
        ,paymentPosServiceInfo.getCVVCode(), paymentPosServiceInfo.getTotalPrice());


    }
}

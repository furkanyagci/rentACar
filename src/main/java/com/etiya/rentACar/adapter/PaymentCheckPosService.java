package com.etiya.rentACar.adapter;

import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;

public interface PaymentCheckPosService {
    boolean Pay(PaymentPosServiceInfo paymentPosServiceInfo);
}

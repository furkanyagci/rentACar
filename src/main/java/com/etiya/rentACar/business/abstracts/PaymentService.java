package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.paymentResponses.PaymentDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.Payment;

import java.util.List;

public interface PaymentService {
    DataResult<PaymentDto> getById(int paymentId);

    DataResult<List<ListPaymentDto>> getAll();
    DataResult<List<ListPaymentDto>> getByCustomerId(int customerId);

    Result add(CreatePaymentRequest createPaymentRequest);
    Result delete(DeletePaymentRequest deletePaymentRequest);
    Result update(UpdatePaymentRequest updatePaymentRequest);

    Result makePayment(CreatePaymentRequest createPaymentRequest);
}

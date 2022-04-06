package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.PaymentService;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.paymentResponses.PaymentDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//
@RequestMapping("/api/payments")
public class PaymentsController {
    private PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getall")
    public DataResult<List<ListPaymentDto>> getAll(){
        return this.paymentService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<PaymentDto> getById(@RequestParam("paymentId") int paymentId) {
        return this.paymentService.getById(paymentId);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return this.paymentService.add(createPaymentRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UpdatePaymentRequest updatePaymentRequest) {
        return this.paymentService.update(updatePaymentRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody DeletePaymentRequest deletePaymentRequest) {
        return this.paymentService.delete(deletePaymentRequest);
    }

}

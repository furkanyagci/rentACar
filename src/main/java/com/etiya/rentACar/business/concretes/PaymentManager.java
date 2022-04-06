package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.adapter.PaymentCheckPosService;
import com.etiya.rentACar.adapter.PaymentPosServiceInfo;
import com.etiya.rentACar.business.abstracts.*;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.customerResponses.CustomerDto;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.paymentResponses.PaymentDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.PaymentDao;
import com.etiya.rentACar.entities.Customer;
import com.etiya.rentACar.entities.Payment;
import com.etiya.rentACar.entities.Rental;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private AdditionalServiceService additionalServiceService;
    private PaymentCheckPosService paymentCheckPosService;
    private CustomerService customerService;
    private RentalService rentalService;
    private InvoiceService invoiceService;
    private RentalAdditionalServiceDetailService rentalAdditionalServiceDetailService;

    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, CarService carService
            ,AdditionalServiceService additionalServiceService, PaymentCheckPosService paymentCheckPosService
            ,CustomerService customerService,InvoiceService invoiceService, RentalService rentalService
            ,RentalAdditionalServiceDetailService rentalAdditionalServiceDetailService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.additionalServiceService = additionalServiceService;
        this.paymentCheckPosService = paymentCheckPosService;
        this.customerService= customerService;
        this.rentalService = rentalService;
        this.rentalAdditionalServiceDetailService = rentalAdditionalServiceDetailService;
    }

    @Override
    public DataResult<PaymentDto> getById(int paymentId) {
        Payment payment = this.paymentDao.getById(paymentId);
        PaymentDto response = this.modelMapperService.forDto().map(payment,PaymentDto.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<ListPaymentDto>> getAll() {
        List<Payment> payments = this.paymentDao.findAll();
        List<ListPaymentDto> response = payments.stream()
                .map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<ListPaymentDto>> getByCustomerId(int customerId) {
        List<Payment> payments = this.paymentDao.findAll();
        List<ListPaymentDto> response = payments.stream()
                .map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {
        Payment payment = this.modelMapperService.forDto().map(createPaymentRequest, Payment.class);

        makePayment(createPaymentRequest);

        this.paymentDao.save(payment);
        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED);
    }

    @Override
    public Result delete(DeletePaymentRequest deletePaymentRequest) {
        this.paymentDao.deleteById(deletePaymentRequest.getId());
        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_DELETED);
    }

    @Override
    public Result update(UpdatePaymentRequest updatePaymentRequest) {
        Payment payment = this.modelMapperService.forDto().map(updatePaymentRequest, Payment.class);
        this.paymentDao.save(payment);
        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_UPDATED);
    }



    @Override
    public Result makePayment(CreatePaymentRequest createPaymentRequest) {
        PaymentPosServiceInfo paymentPosServiceInfo = this.modelMapperService.forDto()
                .map(createPaymentRequest, PaymentPosServiceInfo.class);
        boolean payResult = this.paymentCheckPosService.Pay(paymentPosServiceInfo);
        if(payResult == false){
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMENT_ERROR);
        }

        //rental, additionalservice, invoice
        this.rentalService.add(createPaymentRequest.getCreateRentalRequest());
        this.invoiceService.add(createPaymentRequest.getCreateInvoiceRequest());


        return new SuccessResult();
    }



    private int calculateDay(CreateRentalRequest createRentalRequest) {
        Period diff = Period.between(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate());
        if (diff.getDays() ==0) {
            return 1;
        }
        return diff.getDays();
    }

    private double calculateTotalPrice(CreateRentalRequest createRentalRequest, List<Integer> additionalServiceId) {
        CarDto car = this.carService.getById(createRentalRequest.getCarId().getId()).getData();
        double dailyPrice = car.getDailyPrice();
        int dayCount = calculateDay(createRentalRequest);
        double dayPrice = dayCount * dailyPrice;
        double citySame = this.checkIfCitiesSame(createRentalRequest);
        double additionalServicesTotalPrice = dayCount * this.checkIfAdditionalServices(additionalServiceId);

        return (dayPrice + citySame + additionalServicesTotalPrice);
    }

    private double checkIfCitiesSame(CreateRentalRequest createRentalRequest) {
        if(createRentalRequest.getRentCityId() != createRentalRequest.getReturnedCityId()) {
            return 750;
        }
        return 0;
    }

    private double checkIfAdditionalServices(List<Integer> listAdditionalServiceId) {
        double additionalServicesTotalPrice = 0;

        for (int i = 0; i < listAdditionalServiceId.size(); i++) {
            additionalServicesTotalPrice += this.additionalServiceService.getById(listAdditionalServiceId.get(i)).getData().getDailyPrice();
        }

        return additionalServicesTotalPrice;
    }
}

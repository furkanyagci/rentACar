package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.rentACar.business.responses.invoiceResponses.InvoiceDto;
import com.etiya.rentACar.business.responses.invoiceResponses.ListInvoiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    DataResult<InvoiceDto> getById(int invoiceId);
    DataResult<List<ListInvoiceDto>> getByCustomerId(int customerId);

    DataResult<List<ListInvoiceDto>> getAll();
    DataResult<List<ListInvoiceDto>> getAllCreateDateBetween(LocalDate firstCreateDate, LocalDate endCreateDate);

    DataResult<Invoice> add(CreateInvoiceRequest createInvoiceRequest);
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest);

    int findFirstByOrderByIdDesc();
}

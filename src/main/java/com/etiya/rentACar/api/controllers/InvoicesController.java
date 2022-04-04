package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.rentACar.business.responses.invoiceResponses.InvoiceDto;
import com.etiya.rentACar.business.responses.invoiceResponses.ListInvoiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController//
@RequestMapping("/api/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getall")
    public DataResult<List<ListInvoiceDto>> getAll(){
        return this.invoiceService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<InvoiceDto> getById(@RequestParam("invoiceId") int invoiceId) {
        return this.invoiceService.getById(invoiceId);
    }

    @GetMapping("/getbycustomerid")
    DataResult<List<ListInvoiceDto>> getByCustomerId(int customerId){
        return this.invoiceService.getByCustomerId(customerId);
    }

    @GetMapping("/getallcreatedatebetween")
    DataResult<List<ListInvoiceDto>> getAllCreateDateBetween(@RequestParam("firstcreatedate") LocalDate firstCreateDate,@RequestParam("endcreatedate") LocalDate endCreateDate){
        return this.invoiceService.getAllCreateDateBetween(firstCreateDate,endCreateDate);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.add(createInvoiceRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
        return invoiceService.update(updateInvoiceRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteInvoiceRequest deleteInvoiceRequest) {
        return this.invoiceService.delete(deleteInvoiceRequest);
    }
}

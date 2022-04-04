package com.etiya.rentACar.dataAccess.abctracts;

import com.etiya.rentACar.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {
    List<Invoice> getByCreateDateBetween(LocalDate firstCreateDate, LocalDate endCreateDate);
    List<Invoice> getByCustomerId(int customerId);
}

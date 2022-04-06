package com.etiya.rentACar.dataAccess.abctracts;

import com.etiya.rentACar.entities.Invoice;
import com.etiya.rentACar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Integer> {
    List<Payment> getByCustomerId(int customerId);
}

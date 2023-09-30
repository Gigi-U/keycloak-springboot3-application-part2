package com.dh.msbills.repository;

import com.dh.msbills.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByCustomerBill(String customerBill);

}

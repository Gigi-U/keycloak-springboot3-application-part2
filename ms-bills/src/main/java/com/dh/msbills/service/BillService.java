package com.dh.msbills.service;

import com.dh.msbills.model.Bill;
import com.dh.msbills.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public List<Bill> findByCustomerBill(String id){
        return repository.findByCustomerBill(id);
    }
    public Bill saveBill(Bill bill){
        return repository.save(bill);
    }

}

package com.dh.msusers.repository;

import com.dh.msusers.model.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepository {

    private IFeignBillRepository feignBillRepository;

    public BillRepository(IFeignBillRepository feignBillRepository) {
        this.feignBillRepository = feignBillRepository;
    }
    public List<Bill> findByUserId(String id){
        ResponseEntity<List<Bill>> response = feignBillRepository.findByUserId(id);
        return response.getBody();
    }
}

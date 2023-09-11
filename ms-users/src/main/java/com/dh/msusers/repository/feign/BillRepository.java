package com.dh.msusers.repository.feign;

import com.dh.msusers.model.BillDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepository {

        private IFeignBillRepository feignBillRepository;

    public BillRepository(IFeignBillRepository feignBillRepository) {
        this.feignBillRepository = feignBillRepository;
    }

    public List<BillDTO> findByUserId(String customerBill){
        ResponseEntity<List<BillDTO>> response = feignBillRepository.findByUserId(customerBill);
        return response.getBody();
    }
}

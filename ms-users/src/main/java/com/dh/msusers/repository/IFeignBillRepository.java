package com.dh.msusers.repository;

import com.dh.msusers.configuration.OAuthFeignConfig;
import com.dh.msusers.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name= "ms-bills",url = "http://localhost:8086", configuration = OAuthFeignConfig.class) //"http://localhost: 8086"
public interface IFeignBillRepository {

    @RequestMapping(method = RequestMethod.GET,value = "/bills/find")
    ResponseEntity<List<Bill>> findByUserId(@RequestParam String customerBill);
}

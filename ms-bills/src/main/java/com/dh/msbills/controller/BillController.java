package com.dh.msbills.controller;

import com.dh.msbills.model.Bill;
import com.dh.msbills.service.BillService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService service;

    @Value("${server.port}")
    private int serverPort;
    @GetMapping("/ping")
    public String pong() {
        return "ping-pong";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @PostMapping("/newbill")
    @PreAuthorize("hasAnyAuthority('PROVIDERS')")
    public ResponseEntity <Bill> postNewBill(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.saveBill(bill));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Bill>> findByUserId(@RequestParam String customerBill, HttpServletResponse response){
        response.addHeader("port", String.valueOf(serverPort));

        List<Bill> foundBills = service.findByCustomerBill(customerBill);
        if (foundBills.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(service.findByCustomerBill(customerBill));
        }

    }


}

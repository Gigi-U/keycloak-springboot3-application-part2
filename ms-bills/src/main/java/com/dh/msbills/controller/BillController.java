package com.dh.msbills.controller;

import com.dh.msbills.model.Bill;
import com.dh.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService service;

    @GetMapping("/ping")
    public String pong() {
        return "ping-pong";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_app_user')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    } // http://localhost:8090/api/v1/bills/all  user: gisela password: password

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('GROUP_PROVIDERS')")
    public ResponseEntity <Bill> createNewBill(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.saveNewBill(bill));
    } // http://localhost:8090/api/v1/bills/create user: provider1 password: password

    @GetMapping("/find/{customerBill}")
    public ResponseEntity<List<Bill>> findByCustomerBill(@PathVariable String customerBill) {
        return ResponseEntity.ok().body(service.findByCustomerBill(customerBill));
    }
}

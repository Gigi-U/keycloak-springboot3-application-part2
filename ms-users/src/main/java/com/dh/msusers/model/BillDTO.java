package com.dh.msusers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class BillDTO {
    private String idBill;

    private String customerBill;

    private String productBill;

    private Double totalPrice;

    public BillDTO() {
    }

    public BillDTO(String idBill, String customerBill, String productBill, Double totalPrice) {
        this.idBill = idBill;
        this.customerBill = customerBill;
        this.productBill = productBill;
        this.totalPrice = totalPrice;
    }

    public String getIdBill() {
        return idBill;
    }

    public String getCustomerBill() {
        return customerBill;
    }

    public String getProductBill() {
        return productBill;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setCustomerBill(String customerBill) {
        this.customerBill = customerBill;
    }

    public void setProductBill(String productBill) {
        this.productBill = productBill;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

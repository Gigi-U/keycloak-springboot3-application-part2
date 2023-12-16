package com.dh.msbills.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idBill;
    @Column(name = "CUSTOMER_BILL")
    private String customerBill;
    @Column(name = "PRODUCT_BILL")
    private String productBill;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

}

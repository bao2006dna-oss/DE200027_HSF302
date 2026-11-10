package com.de200027.mvc.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Integer dviceId;


    @Column(name = "device_name", nullable = false, length = 6)

    private String deviceName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catergory_id", nullable = false)
    private Category category;
    @Column(name = "price", precision = 10, scale = 0, nullable = false)

    private BigDecimal price;
    @Column(name = "warranty_months", nullable = false)
    private int warrantyMonths;
    @Column(name = "installation_dates", nullable = false)
    private LocalDate installationDate;
    @Column(name = "status", nullable = false, length = 10)
    private String status;

    public Device(Integer dviceId) {
        this.dviceId = dviceId;
    }

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }

    public Device(Category category) {
        this.category = category;
    }

    public Device(BigDecimal price) {
        this.price = price;
    }

    public Device(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }


    public Device(LocalDate installationDate) {
        this.installationDate = installationDate;
    }
















}

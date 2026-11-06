package com.de200027.mvc.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="device_id")
    private Integer dviceId;



    @Column(name="device_name", nullable = false, length =6)

   private String deviceName;
    @ManyToOne(fetch =FetchTypeEaGer)











}

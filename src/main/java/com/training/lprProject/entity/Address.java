package com.training.lprProject.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", unique = true, nullable = false)
    Long addressId;
    @Column(name = "COUNTRY", nullable = false, length = 40)
    String country;
    @Column(name = "REGION", nullable = false, length = 40)
    String region;
    @Column(name = "CITY", nullable = false, length = 40)
    String city;
    @Column(name = "STREET", nullable = false, length = 40)
    String street;
}

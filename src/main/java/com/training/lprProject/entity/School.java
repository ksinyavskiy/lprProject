package com.training.lprProject.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "SCHOOL")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHOOL_ID", unique = true, nullable = false)
    Long schoolId;
    @Column(name = "NAME", unique = true, nullable = false, length = 50)
    String name;
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    Address address;
    @OneToMany
    Set<User> users;
}

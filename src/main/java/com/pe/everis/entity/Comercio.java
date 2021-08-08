package com.pe.everis.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comercio")
@Getter
@Setter
public class Comercio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="informacion")
    private String informacion;
    
    @Column(name="fecha")
    private LocalDate fecha;

}

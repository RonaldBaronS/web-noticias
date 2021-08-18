package com.pe.unmsm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sbs")
@Getter
@Setter
public class Sbs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="titulo",length = 500)
    private String titulo;
    
    @Column(name="informacion",length = 500)
    private String informacion;
    
    @Column(name="fecha")
    private String fecha;
    
}

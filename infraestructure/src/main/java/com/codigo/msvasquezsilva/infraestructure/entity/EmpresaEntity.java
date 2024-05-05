package com.codigo.msvasquezsilva.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "empresa")
@Getter
@Setter


public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Long idEmpresa;

    private String razonSocial;
    private String tipoDocumento;
    private String numeroDocumento;
    private String estado;
    private String condicion;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private Boolean EsAgenteRetencion;
    private String usuacrea;
    private Timestamp datecreate;
    private String usuamodif;
    private Timestamp datemodif;
    private String usuadelet;
    private Timestamp datedelet;


}

package com.codigo.msvasquezsilva.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="persona")
@Getter
@Setter

public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idPersona;

    @Column(name="numeroDocumento",length=20)
    private String numDoc;

    @Column(name="nombre",length=255)
    private String nombres;

    @Column(name="apellido", length=255)
    private String apellido;

    @Column(name="estado")
    private Integer estado;

    @Column(name="usua_crea",length=255)
    private String usuaCrea;

    @Column(name="date_create")
    private Timestamp dateCreate;

    @Column(name="usua_modif",length=255)
    private String usuaModif;

    @Column(name="date_modif")
    private Timestamp dateModif;

    @Column(name="usua_delet",length=255)
    private String usuaDelet;

    @Column(name="date_delet")
    private Timestamp dateDelet;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_empresa")
    private EmpresaEntity idEmpresa;


}

package com.codigo.msvasquezsilva.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class EmpresaDto {

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
    private Boolean esAgenteRetencion;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}

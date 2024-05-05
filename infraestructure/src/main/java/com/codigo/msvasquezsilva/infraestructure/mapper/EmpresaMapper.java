package com.codigo.msvasquezsilva.infraestructure.mapper;

import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.infraestructure.entity.EmpresaEntity;

import java.sql.Timestamp;


public class EmpresaMapper {

    public static EmpresaDto fromEntity(EmpresaEntity entity){
        EmpresaDto dto = new EmpresaDto();
        dto.setIdEmpresa(entity.getIdEmpresa());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setTipoDocumento(entity.getTipoDocumento());
        dto.setNumeroDocumento(entity.getNumeroDocumento());
        dto.setEstado(entity.getEstado());
        dto.setCondicion(entity.getCondicion());
        dto.setDireccion(entity.getDireccion());
        dto.setDistrito(entity.getDistrito());
        dto.setProvincia(entity.getProvincia());
        dto.setDepartamento(entity.getDepartamento());
        dto.setEsAgenteRetencion(entity.getEsAgenteRetencion());
        dto.setUsuaCrea(entity.getUsuacrea());
        dto.setDateCreate(entity.getDatecreate());
        dto.setUsuaModif(entity.getUsuamodif());
        dto.setDateModif(entity.getDatemodif());
        dto.setUsuaDelet(entity.getUsuadelet());
        dto.setDateDelet(entity.getDatedelet());
        return dto;
    }

}

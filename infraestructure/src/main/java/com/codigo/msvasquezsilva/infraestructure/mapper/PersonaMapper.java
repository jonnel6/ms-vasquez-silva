package com.codigo.msvasquezsilva.infraestructure.mapper;

import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {

    public static PersonaDto fromEntity(PersonaEntity entity){
        PersonaDto dto = new PersonaDto();
        dto.setIdPersona(entity.getIdPersona());
        dto.setNumeroDocumento(entity.getNumDoc());
        dto.setNombre(entity.getNombres());
        dto.setApellido(entity.getApellido());
        dto.setEstado(entity.getEstado());
        dto.setUsuaCrea(entity.getUsuaCrea());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUsuaModif(entity.getUsuaModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUsuaDelet(entity.getUsuaDelet());
        dto.setDateDelet(entity.getDateDelet());

        if (entity.getIdEmpresa() != null) {
            dto.setEmpresa_id(entity.getIdEmpresa().getIdEmpresa());
        }


        return dto;
    }
}

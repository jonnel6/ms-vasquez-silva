package com.codigo.msvasquezsilva.domain.ports.out;

import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {

    EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarEmpresaPorIdOut(Long id);
    List<EmpresaDto> buscarTodasLasEmpresasOut();
    EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest);
    EmpresaDto borrarEmpresaOut(Long id);
}

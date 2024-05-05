package com.codigo.msvasquezsilva.domain.ports.in;

import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {

    EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarEmpresaPorIdIn(Long id);
    List<EmpresaDto> buscarTodasLasEmpresasIn();
    EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDto borrarEmpresaIn(Long id);



}

package com.codigo.msvasquezsilva.domain.impl;

import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvasquezsilva.domain.ports.in.EmpresaServiceIn;
import com.codigo.msvasquezsilva.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class EmpresaServiceImpl implements EmpresaServiceIn {

    public final EmpresaServiceOut empresaServiceOut;
    @Override
    public EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);

    }

    @Override
    public Optional<EmpresaDto> buscarEmpresaPorIdIn(Long id) {
        return empresaServiceOut.buscarEmpresaPorIdOut(id);
    }

    @Override
    public List<EmpresaDto> buscarTodasLasEmpresasIn() {
        return empresaServiceOut.buscarTodasLasEmpresasOut();
    }

    @Override
    public EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarEmpresaOut(id, empresaRequest);
    }

    @Override
    public EmpresaDto borrarEmpresaIn(Long id) {
        return empresaServiceOut.borrarEmpresaOut(id);
    }


}

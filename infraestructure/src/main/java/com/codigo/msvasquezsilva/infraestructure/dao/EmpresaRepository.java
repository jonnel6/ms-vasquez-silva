package com.codigo.msvasquezsilva.infraestructure.dao;

import com.codigo.msvasquezsilva.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    EmpresaEntity findIdByNumeroDocumento(String numeroDocumento);
}

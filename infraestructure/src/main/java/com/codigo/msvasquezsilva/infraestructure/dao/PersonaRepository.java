package com.codigo.msvasquezsilva.infraestructure.dao;

import com.codigo.msvasquezsilva.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}

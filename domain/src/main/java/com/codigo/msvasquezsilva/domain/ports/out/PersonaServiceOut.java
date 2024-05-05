package com.codigo.msvasquezsilva.domain.ports.out;

import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {

    PersonaDto crearPersonaOut(PersonaRequest personaRequest);
    Optional<PersonaDto> buscarxIdOut(Long id);
    List<PersonaDto> obtenerTodosOut();
    PersonaDto actualizarOut(Long id, PersonaRequest personaRequest);
    PersonaDto deleteOut(Long id);

}

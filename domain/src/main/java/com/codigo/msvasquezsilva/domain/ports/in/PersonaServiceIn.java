package com.codigo.msvasquezsilva.domain.ports.in;

import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDto crearPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDto> buscarxIdIn(Long id);
    List<PersonaDto> obtenerTodosIn();
    PersonaDto actualizarIn(Long id, PersonaRequest personaRequest);
    PersonaDto deleteIn(Long id);

}

package com.codigo.msvasquezsilva.domain.impl;

import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;
import com.codigo.msvasquezsilva.domain.ports.in.PersonaServiceIn;
import com.codigo.msvasquezsilva.domain.ports.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    public final PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDto crearPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearPersonaOut(personaRequest);
    }

    @Override
    public Optional<PersonaDto> buscarxIdIn(Long id) {
        return personaServiceOut.buscarxIdOut(id);
    }

    @Override
    public List<PersonaDto> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDto actualizarIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.actualizarOut(id, personaRequest);
    }

    @Override
    public PersonaDto deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}

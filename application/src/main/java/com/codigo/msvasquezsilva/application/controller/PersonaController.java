package com.codigo.msvasquezsilva.application.controller;

import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;
import com.codigo.msvasquezsilva.domain.ports.in.PersonaServiceIn;
import feign.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-vasquez-silva/v1/persona")
@AllArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<PersonaDto> registrar(@RequestBody PersonaRequest requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.buscarxIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<PersonaDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.obtenerTodosIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> actualizar(@PathVariable Long id, @RequestBody PersonaRequest requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.actualizarIn(id,requestPersona));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.deleteIn(id));

    }


}

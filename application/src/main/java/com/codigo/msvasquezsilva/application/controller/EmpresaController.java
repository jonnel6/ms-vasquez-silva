package com.codigo.msvasquezsilva.application.controller;

import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;
import com.codigo.msvasquezsilva.domain.ports.in.EmpresaServiceIn;
import com.codigo.msvasquezsilva.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ms-vasquez-silva/v1/empresa")
@AllArgsConstructor

public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<EmpresaDto> registrar(@RequestBody EmpresaRequest requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(requestEmpresa));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarEmpresaPorIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpresaDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarTodasLasEmpresasIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> actualizar(@PathVariable Long id, @RequestBody EmpresaRequest requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.actualizarEmpresaIn(id,requestEmpresa));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.borrarEmpresaIn(id));

    }



}

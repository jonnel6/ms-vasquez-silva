package com.codigo.msvasquezsilva.infraestructure.adapters;

import com.codigo.msvasquezsilva.domain.aggregates.constants.Constant;
import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.ReniecDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.PersonaRequest;
import com.codigo.msvasquezsilva.domain.ports.out.PersonaServiceOut;
import com.codigo.msvasquezsilva.infraestructure.client.ClientReniec;
import com.codigo.msvasquezsilva.infraestructure.dao.EmpresaRepository;
import com.codigo.msvasquezsilva.infraestructure.dao.PersonaRepository;
import com.codigo.msvasquezsilva.infraestructure.entity.EmpresaEntity;
import com.codigo.msvasquezsilva.infraestructure.entity.PersonaEntity;
import com.codigo.msvasquezsilva.infraestructure.mapper.PersonaMapper;
import com.codigo.msvasquezsilva.infraestructure.redis.RedisService;
import com.codigo.msvasquezsilva.infraestructure.util.Util;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final EmpresaRepository empresaRepository;
    private final ClientReniec clientReniec;
    private final RedisService redisService;

    @Value("${token.resu}")
    private String tokenReniec;


    @Override
    public PersonaDto crearPersonaOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest, false, null);
        return PersonaMapper.fromEntity((personaRepository.save(personaEntity)));
    }

    private PersonaEntity getEntity(PersonaRequest personaRequest, boolean actualiza, Long id){
       // Exec servicio
       ReniecDto reniecDto = getExecReniec(personaRequest.getNumDoc());

       PersonaEntity entity = new PersonaEntity();
        entity.setNumDoc(reniecDto.getNumeroDocumento());
        entity.setNombres(reniecDto.getNombres());
        entity.setApellido(reniecDto.getApellidoPaterno() + " "+ reniecDto.getApellidoMaterno());
        entity.setEstado(Constant.STATUS_ACTIVE);
        EmpresaEntity temporal = empresaRepository.findIdByNumeroDocumento(personaRequest.getEmpresa());
        if (temporal != null) {
            entity.setIdEmpresa(temporal);
        }
        if(actualiza){
            // si actualizo hago esto
            entity.setIdPersona(id);
            entity.setUsuaModif(Constant.USUA_ADMIN);
            entity.setDateModif(getTimestamp());
        }else{
            // si no actualizo hago esto
            // auditoria

            entity.setUsuaCrea(Constant.USUA_ADMIN);
            entity.setDateCreate(getTimestamp());

        }


        return entity;


    }

    private ReniecDto getExecReniec(String numDoc){
        String authorization = "Bearer "+tokenReniec;
        return clientReniec.getInfoReniec(numDoc,authorization);
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    @Override
    public Optional<PersonaDto> buscarxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA+id);
        if(redisInfo != null){
            PersonaDto personaDto = Util.convertirDesdeString(redisInfo, PersonaDto.class);
            return Optional.of(personaDto);
        }else{
            PersonaDto personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA+id, dataForRedis, 2);

        return Optional.of(personaDto);
        }
    }

    @Override
    public List<PersonaDto> obtenerTodosOut() {
        List<PersonaDto> listaDto = new ArrayList<>();
        List<PersonaEntity> entidades = personaRepository.findAll();
        for(PersonaEntity dato: entidades){
            listaDto.add(PersonaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public PersonaDto actualizarOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if(datoExtraido.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,true, id);
            personaEntity.setUsuaCrea(datoExtraido.get().getUsuaCrea());
            personaEntity.setDateCreate(datoExtraido.get().getDateCreate());
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();

        }

    }

    @Override
    public PersonaDto deleteOut(Long id) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USUA_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return PersonaMapper.fromEntity(personaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}

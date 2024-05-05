package com.codigo.msvasquezsilva.infraestructure.adapters;

import com.codigo.msvasquezsilva.domain.aggregates.constants.Constant;
import com.codigo.msvasquezsilva.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.PersonaDto;
import com.codigo.msvasquezsilva.domain.aggregates.dto.SunatDto;
import com.codigo.msvasquezsilva.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvasquezsilva.domain.ports.out.EmpresaServiceOut;
import com.codigo.msvasquezsilva.infraestructure.client.ClientSunat;
import com.codigo.msvasquezsilva.infraestructure.dao.EmpresaRepository;
import com.codigo.msvasquezsilva.infraestructure.entity.EmpresaEntity;
import com.codigo.msvasquezsilva.infraestructure.entity.PersonaEntity;
import com.codigo.msvasquezsilva.infraestructure.mapper.EmpresaMapper;
import com.codigo.msvasquezsilva.infraestructure.mapper.PersonaMapper;
import com.codigo.msvasquezsilva.infraestructure.redis.RedisService;
import com.codigo.msvasquezsilva.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class EmpresaAdapter implements EmpresaServiceOut {


    private final EmpresaRepository empresaRepository;
    private final ClientSunat clientSunat;
    private final RedisService redisService;

    @Value("${token.resu}")
    private String tokenSunat;


    @Override
    public EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity =getEntity(empresaRequest, false, null);
        return EmpresaMapper.fromEntity((empresaRepository.save(empresaEntity)));
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualiza, Long id){
        // Exec servicio
        SunatDto sunatDto = getExecSunat(empresaRequest.getNumDoc());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        entity.setRazonSocial(sunatDto.getRazonSocial());
        entity.setTipoDocumento(sunatDto.getTipoDocumento());
        entity.setEstado(sunatDto.getEstado());
        entity.setCondicion(sunatDto.getCondicion());
        entity.setEsAgenteRetencion(sunatDto.getEsAgenteRetencion());
        if(actualiza){
            // si actualizo hago esto
            entity.setIdEmpresa(id);
            entity.setUsuamodif(Constant.USUA_ADMIN);
            entity.setDatemodif(getTimestamp());
        }else{
            // si no actualizo hago esto
            // auditoria
            entity.setUsuacrea(Constant.USUA_ADMIN);
            entity.setDatecreate(getTimestamp());
        }

        return entity;

    }

    private SunatDto getExecSunat(String numDoc){
        String authorization = "Bearer "+tokenSunat;
        return clientSunat.getInfoSunat(numDoc,authorization);
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }


    @Override
    public Optional<EmpresaDto> buscarEmpresaPorIdOut(Long id) {

        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id);
        if(redisInfo != null){
            EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo, EmpresaDto.class);
            return Optional.of(empresaDto);
        }else{
            EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringB(empresaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id, dataForRedis, 2);
            return Optional.of(empresaDto);
        }

    }

    @Override
    public List<EmpresaDto> buscarTodasLasEmpresasOut() {
        List<EmpresaDto> listaDto = new ArrayList<>();
        List<EmpresaEntity> entidades = empresaRepository.findAll();
        for(EmpresaEntity dato: entidades){
            listaDto.add(EmpresaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            EmpresaEntity empresaEntity = getEntity(empresaRequest,true, id);
            empresaEntity.setUsuacrea(datoExtraido.get().getUsuacrea());
            empresaEntity.setDatecreate(datoExtraido.get().getDatecreate());
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
        }else {
            throw new RuntimeException();

        }
    }

    @Override
    public EmpresaDto borrarEmpresaOut(Long id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado("ELIMINADO");
            datoExtraido.get().setUsuadelet(Constant.USUA_ADMIN);
            datoExtraido.get().setDatedelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}

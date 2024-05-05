package com.codigo.msvasquezsilva.infraestructure.client;

import com.codigo.msvasquezsilva.domain.aggregates.dto.ReniecDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client-reniec", url="https://api.apis.net.pe/v2/reniec/")
public interface ClientReniec {

    @GetMapping("/dni")
    ReniecDto getInfoReniec(@RequestParam("numero") String numero,
                            @RequestHeader("Authorization") String authorization);
}

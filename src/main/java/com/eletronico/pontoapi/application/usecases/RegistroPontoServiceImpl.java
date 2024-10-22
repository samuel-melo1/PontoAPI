package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.entrypoint.dto.request.RegistroPontoDTO;
import com.eletronico.pontoapi.infrastructure.persistence.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroPontoServiceImpl {

    @Autowired
    private RegistroPontoRepository repository;
    public RegistroPontoDTO sendCreate(RegistroPontoDTO dto){
    return null;
    }

}

package com.rms.rms.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface BaseService<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY>{

    List<RESPONSE_DTO> findAll();
    RESPONSE_DTO findById(Long id);
    RESPONSE_DTO save(CREATE_DTO createDto);

}

package com.rms.rms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMapper<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> {

    private final Class<RESPONSE_DTO> responseDtoClass;
    private final Class<ENTITY> entityClass;
    private ModelMapper modelMapper;

    public BaseMapper(Class<RESPONSE_DTO> responseDtoClass, Class<ENTITY> entityClass) {
        this.responseDtoClass = responseDtoClass;
        this.entityClass = entityClass;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ENTITY createDtoToEntity(CREATE_DTO create_dto) {
        return modelMapper.map(create_dto, entityClass);
    }

    public RESPONSE_DTO entityToResponseDto(ENTITY entity) {
        return modelMapper.map(entity, responseDtoClass);
    }

    public ENTITY updateDtoToEntity(UPDATE_DTO update_dto) {
        return modelMapper.map(update_dto, entityClass);
    }


}

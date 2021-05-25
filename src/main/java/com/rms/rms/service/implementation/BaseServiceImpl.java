package com.rms.rms.service.implementation;

import com.rms.rms.mapper.BaseMapper;
import com.rms.rms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BaseServiceImpl<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> implements BaseService<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> {

    @Autowired
    protected JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired
    protected BaseMapper<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> baseMapper;

    @Override
    public List<RESPONSE_DTO> findAll() {
        return jpaRepository.findAll()
                            .stream()
                            .map(baseMapper::entityToResponseDto)
                            .collect(Collectors.toList());
    }

    @Override
    public RESPONSE_DTO findById(Long id) {
        return jpaRepository.findById(id)
                .map(baseMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    throw new RuntimeException("Entity with id: " + id + " does not exist!");
                });
    }

    @Override
    public RESPONSE_DTO save(CREATE_DTO entity) {
        return baseMapper.entityToResponseDto(jpaRepository.save(baseMapper.createDtoToEntity(entity)));
    }

}

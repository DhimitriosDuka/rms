package com.rms.rms.service.implementation;

import com.rms.rms.mapper.BaseMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
abstract class BaseServiceTest<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY>{

    @Mock
    protected JpaRepository<ENTITY, Long> jpaRepository;

    @Mock
    protected BaseMapper<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> baseMapper;

    abstract void init();

}

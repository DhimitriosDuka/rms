package com.rms.rms.service;

import com.rms.rms.exception.NullParameterException;

import java.util.Objects;

public class SuperService {

    public void checkNullabilityOfParameters(Object... objects) {
        for (Object o : objects) {
            if(Objects.isNull(o)) throw new NullParameterException("Parameter/s must not be null!");
        }
    }

}

package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.RecuperacionDto;
import com.example.GymAdmin.entity.RecuperacionEntity;
import org.springframework.http.ResponseEntity;

public interface IRecuperacionService {
    public ResponseEntity<String> stepOneRecuperacion(RecuperacionDto recuperacionDto);

    public ResponseEntity<String> stepThowRecuperacion(RecuperacionDto recuperacionDto);


}

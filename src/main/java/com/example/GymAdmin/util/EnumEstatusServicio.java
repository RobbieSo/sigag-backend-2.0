package com.example.GymAdmin.util;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EnumEstatusServicio {
    ESTATUS_ACTIVE("01", "Servicio activo"),
    ESTATUS_NO_ACTIVE("02", "Servicio no activo"),
    ESTATUS_PPA("03", "Servicio pendiente por activar");

    private final String code;
    private final String description;
}

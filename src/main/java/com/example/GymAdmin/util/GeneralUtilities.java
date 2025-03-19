package com.example.GymAdmin.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class GeneralUtilities {

    public LocalDateTime calculateVigencia(String plan, LocalDateTime fechaInicio){

        LocalDateTime localDateTime = LocalDateTime.now();
        switch (plan) {
            case "diario":
                localDateTime=  fechaInicio.plusDays(1);
                break;
            case "semanal":
                localDateTime=  fechaInicio.plusWeeks(1);
                break;
            case "mensual" :
                localDateTime=   fechaInicio.plusMonths(1);
                break;

            case "anual" :
                localDateTime=  fechaInicio.plusYears(1);
                break;
            default:
        }

        return localDateTime;
    }
}

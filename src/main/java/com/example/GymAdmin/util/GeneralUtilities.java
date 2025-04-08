package com.example.GymAdmin.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class GeneralUtilities {

    public LocalDate calculateVigencia(String plan, LocalDate fechaInicio) {

        LocalDate vigencia;
        if (plan.equalsIgnoreCase("diario")) {
            vigencia = fechaInicio.plusDays(1);

        } else if (plan.equalsIgnoreCase("semanal")) {
            vigencia = fechaInicio.plusWeeks(1);

        } else if (plan.equalsIgnoreCase("mensual")) {
            vigencia = fechaInicio.plusMonths(1);

        } else if (plan.equalsIgnoreCase("anual")) {
            vigencia = fechaInicio.plusYears(1);

        } else {
            vigencia = LocalDate.now();
        }


        return vigencia;
    }
}

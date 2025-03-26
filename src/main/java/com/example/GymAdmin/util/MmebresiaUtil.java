package com.example.GymAdmin.util;

import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MmebresiaUtil {

    public static Boolean calculateAdd(MembresiaResponse memebresia){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Boolean flag = false;

        LocalDateTime fechaVigencia = LocalDateTime.parse(memebresia.getVigencia());

        LocalDateTime fechaActual = LocalDateTime.now();


        if(memebresia.getTipo().equalsIgnoreCase("semanal")){
            LocalDateTime fechaPreventiva = fechaVigencia.minusDays(3);
            flag=  fechaPreventiva.isBefore(fechaActual);

        } else if (memebresia.getTipo().equalsIgnoreCase("mensual")) {
            LocalDateTime fechaPreventiva = fechaVigencia.minusDays(10);
            flag=  fechaPreventiva.isBefore(fechaActual);

        } else if (memebresia.getTipo().equalsIgnoreCase("anual")) {
            LocalDateTime fechaPreventiva = fechaVigencia.minusDays(20);
            flag=  fechaPreventiva.isBefore(fechaActual);

        }

        return flag;
    }
}


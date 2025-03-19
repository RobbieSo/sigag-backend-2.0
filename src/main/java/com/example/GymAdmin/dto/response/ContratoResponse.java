package com.example.GymAdmin.dto.response;

import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContratoResponse {
    private ClienteResponse cliente;
    private MembresiaResponse membresia;
    private List<PagoResponse> pagos;
}

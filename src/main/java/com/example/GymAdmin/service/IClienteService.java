package com.example.GymAdmin.service;

import com.example.GymAdmin.dto.request.AsistenciaRequest;
import com.example.GymAdmin.dto.request.AsistenciaResponseCr;
import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.AsistenciaResponse;
import com.example.GymAdmin.dto.response.ClienteResponse;

import java.util.List;

public interface IClienteService extends ICrudService<ClienteRequest, ClienteResponse, Integer> {
    public List<ClienteResponse> getClientesPorVencer();

    public AsistenciaResponse getRegistraAsistencia(AsistenciaRequest asistenciaRequest);

    public List<AsistenciaResponseCr> getAsistenciasByCliente(Integer idCliente);


}

package com.example.GymAdmin.service;

import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;

import java.util.List;

public interface IClienteService extends ICrudService<ClienteRequest, ClienteResponse, Integer>

{
    public List<ClienteResponse> getClientesPorVencer();

}

package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.request.ContratoRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.response.ContratoResponse;
import com.example.GymAdmin.dto.response.PagoResponse;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.ContratoEntity;
import com.example.GymAdmin.entity.MembresiaEntity;
import com.example.GymAdmin.entity.PagoEntity;
import com.example.GymAdmin.repository.*;
import com.example.GymAdmin.service.IContratoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.GymAdmin.service.impl.ClienteServiceImpl.getClienteResponse;
import static com.example.GymAdmin.service.impl.membresias.MebresiaServiceImpl.getMembresiaResponse;
import static com.example.GymAdmin.service.impl.membresias.MebresiaServiceImpl.getServicioResponses;


@Service
public class ContratoServiceImpl implements IContratoService {

    private final IContratoRepository iContratoRepository;
    private final IClienteRepository iClienteRepository;
    private final IMembresiaRepository iMembresiaRepository;
    private final IPagoRepository iPagoRepository;

    public ContratoServiceImpl(IContratoRepository iContratoRepository, IClienteRepository iClienteRepository, IMembresiaRepository iMembresiaRepository, IPagoRepository iPagoRepository) {
        this.iContratoRepository = iContratoRepository;
        this.iClienteRepository = iClienteRepository;
        this.iMembresiaRepository = iMembresiaRepository;
        this.iPagoRepository = iPagoRepository;
    }

    @Override
    public ContratoResponse create(ContratoRequest request) {
        ContratoEntity contratoEntity = new ContratoEntity();
        ClienteEntity cliente = iClienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        MembresiaEntity member = iMembresiaRepository.findById(request.getIdMembresia()).orElseThrow(() -> new RuntimeException("Membresia no encontrada"));
        contratoEntity.setCliente(cliente);
        contratoEntity.setMembresia(member);
        contratoEntity = iContratoRepository.save(contratoEntity);

        ClienteResponse clienteResponse = getClienteResponse(cliente);
        MembresiaResponse membresiaResponse = getMembresiaResponse(member);
        if (!member.getServicios().isEmpty()) {
            List<ServicioResponse> services = getServicioResponses(member);
            membresiaResponse.setServicios(services);
        }

        return getContratoResponse(contratoEntity, clienteResponse, membresiaResponse);
    }

    @Override
    public ContratoResponse find(Integer id) {
        ContratoEntity contratoEntity = iContratoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        ClienteResponse clienteResponse = getClienteResponse(contratoEntity.getCliente());

        MembresiaResponse membresiaResponse = getMembresiaResponse(contratoEntity.getMembresia());
        if (contratoEntity.getMembresia().getServicios() != null && !contratoEntity.getMembresia().getServicios().isEmpty()) {
            List<ServicioResponse> services = getServicioResponses(contratoEntity.getMembresia());
            membresiaResponse.setServicios(services);
        }
        return getContratoResponse(contratoEntity, clienteResponse, membresiaResponse);
    }

    @Override
    public List<ContratoResponse> findAll() {
        List<ContratoEntity> contratos = iContratoRepository.findAll();
        List<ContratoResponse> responseList = new ArrayList<>();
        for (ContratoEntity contrato : contratos) {
            ClienteResponse clienteResponse = getClienteResponse(contrato.getCliente());
            MembresiaResponse membresiaResponse = getMembresiaResponse(contrato.getMembresia());
            if (contrato.getMembresia().getServicios() != null && !contrato.getMembresia().getServicios().isEmpty()) {
                List<ServicioResponse> services = getServicioResponses(contrato.getMembresia());
                membresiaResponse.setServicios(services);
            }
            responseList.add(getContratoResponse(contrato, clienteResponse, membresiaResponse));
        }
        return responseList;
    }

    @Override
    public ContratoResponse update(ContratoRequest request, Integer id) {
        ContratoEntity contratoEntity = iContratoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        ClienteEntity cliente = iClienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        MembresiaEntity member = iMembresiaRepository.findById(request.getIdMembresia()).orElseThrow(() -> new RuntimeException("Membresia no encontrada"));
        contratoEntity.setCliente(cliente);
        contratoEntity.setMembresia(member);
        contratoEntity = iContratoRepository.save(contratoEntity);
        ClienteResponse clienteResponse = getClienteResponse(cliente);
        MembresiaResponse membresiaResponse = getMembresiaResponse(member);
        if (contratoEntity.getMembresia().getServicios() != null && !contratoEntity.getMembresia().getServicios().isEmpty()) {
            List<ServicioResponse> services = getServicioResponses(contratoEntity.getMembresia());
            membresiaResponse.setServicios(services);
        }
        return getContratoResponse(contratoEntity, clienteResponse, membresiaResponse);
    }

    @Override
    public void delete(Integer id) {
        ContratoEntity contratoEntity = iContratoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        iContratoRepository.delete(contratoEntity);
    }

    private static ContratoResponse getContratoResponse(ContratoEntity contratoEntity, ClienteResponse clienteResponse, MembresiaResponse membresiaResponse) {
        ContratoResponse response = new ContratoResponse();
        List<PagoResponse> pagosResponse = new ArrayList<>();
        if (contratoEntity.getPagos() != null && !contratoEntity.getPagos().isEmpty()) {
            for (PagoEntity sub : contratoEntity.getPagos()) {
                PagoResponse pagoResponse = new PagoResponse();
                pagoResponse.setIdPago(sub.getIdPago());
                pagoResponse.setMetodoPago(sub.getMetodoPago());
                pagoResponse.setFechaPagoEfectuado(sub.getFechaPagoEfectuado().toString());
                pagoResponse.setMonto(sub.getMonto());
                pagosResponse.add(pagoResponse);
            }
        }
        response.setCliente(clienteResponse);
        response.setMembresia(membresiaResponse);
        response.setPagos(pagosResponse);
        return response;
    }
}


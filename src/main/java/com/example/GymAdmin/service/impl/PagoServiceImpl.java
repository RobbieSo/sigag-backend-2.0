package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.request.DetallePagoRequest;
import com.example.GymAdmin.dto.request.PagoRequest;
import com.example.GymAdmin.dto.response.DetallePagoResponse;
import com.example.GymAdmin.dto.response.PagoResponse;
import com.example.GymAdmin.entity.ContratoEntity;
import com.example.GymAdmin.entity.DetallePagoEntity;
import com.example.GymAdmin.entity.PagoEntity;
import com.example.GymAdmin.repository.IContratoRepository;
import com.example.GymAdmin.repository.IPagoRepository;
import com.example.GymAdmin.service.IContratoService;
import com.example.GymAdmin.service.IPagoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class PagoServiceImpl implements IPagoService {

    private final IPagoRepository iPagoRepository;
    private final IContratoRepository iContratoRepository;

    public PagoServiceImpl(IPagoRepository iPagoRepository, IContratoRepository iContratoRepository) {
        this.iPagoRepository = iPagoRepository;
        this.iContratoRepository = iContratoRepository;
    }

    @Override
    public PagoResponse create(PagoRequest request) {
        ContratoEntity contratoEntity = iContratoRepository.findById(request.getIdContrato()).orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        PagoEntity pagoEntity = new PagoEntity();
        List<DetallePagoEntity> detallePagoEntities = new ArrayList<>();

        for (DetallePagoRequest sub : request.getDetallePagos()) {
            DetallePagoEntity detallePagoEntity = new DetallePagoEntity();
            detallePagoEntity.setItem(sub.getItem());
            detallePagoEntity.setMonto(sub.getMonto());
            detallePagoEntity.setDescripcion(sub.getDescripcion());
            detallePagoEntity.setPago(pagoEntity);
            detallePagoEntities.add(detallePagoEntity);
        }

        pagoEntity.setMetodoPago(request.getMetodoPago());
        pagoEntity.setMonto(request.getMonto());
        pagoEntity.setFechaPagoEfectuado(LocalDate.now());
        pagoEntity.setDetallePagos(detallePagoEntities);
        pagoEntity.setContrato(contratoEntity);
        pagoEntity = iPagoRepository.save(pagoEntity);

        return getPagoResponse(pagoEntity);
    }

    @Override
    public PagoResponse find(Integer id) {
        PagoEntity pagoEntity = iPagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        return getPagoResponse(pagoEntity);
    }

    @Override
    public List<PagoResponse> findAll() {
        List<PagoEntity> pagos = iPagoRepository.findAll();
        List<PagoResponse> responseList = new ArrayList<>();
        for (PagoEntity pago : pagos) {
            responseList.add(getPagoResponse(pago));
        }
        return responseList;
    }

    @Override
    public PagoResponse update(PagoRequest request, Integer id) {
        PagoEntity pagoEntity = iPagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pagoEntity.setMetodoPago(request.getMetodoPago());
        pagoEntity.setMonto(request.getMonto());
        pagoEntity.setFechaPagoEfectuado(LocalDate.now());

        List<DetallePagoEntity> detallePagoEntities = new ArrayList<>();
        for (DetallePagoRequest sub : request.getDetallePagos()) {
            DetallePagoEntity detallePagoEntity = new DetallePagoEntity();
            detallePagoEntity.setItem(sub.getItem());
            detallePagoEntity.setMonto(sub.getMonto());
            detallePagoEntity.setDescripcion(sub.getDescripcion());
            detallePagoEntity.setPago(pagoEntity);
            detallePagoEntities.add(detallePagoEntity);
        }
        pagoEntity.setDetallePagos(detallePagoEntities);
        pagoEntity = iPagoRepository.save(pagoEntity);

        return getPagoResponse(pagoEntity);
    }

    @Override
    public void delete(Integer id) {
        PagoEntity pagoEntity = iPagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        iPagoRepository.delete(pagoEntity);
    }

    private static PagoResponse getPagoResponse(PagoEntity pagoEntity) {
        PagoResponse pagoResponse = new PagoResponse();
        List<DetallePagoResponse> detallePagoResponses = new ArrayList<>();

        if (pagoEntity.getDetallePagos() != null && !pagoEntity.getDetallePagos().isEmpty()) {
            for (DetallePagoEntity sub : pagoEntity.getDetallePagos()) {
                DetallePagoResponse detallePagoResponse = new DetallePagoResponse();
                detallePagoResponse.setIdDetallepago(sub.getIdDetallePago());
                detallePagoResponse.setMonto(sub.getMonto());
                detallePagoResponse.setItem(sub.getItem());
                detallePagoResponse.setDescripcion(sub.getDescripcion());
                detallePagoResponses.add(detallePagoResponse);
            }
        }

        pagoResponse.setIdPago(pagoEntity.getIdPago());
        pagoResponse.setMetodoPago(pagoEntity.getMetodoPago());
        pagoResponse.setMonto(pagoEntity.getMonto());
        pagoResponse.setIdContrato(pagoEntity.getContrato().getIdContrato());
        pagoResponse.setFechaPagoEfectuado(pagoEntity.getFechaPagoEfectuado().toString());
        pagoResponse.setDetallePagos(detallePagoResponses);
        return pagoResponse;
    }
}

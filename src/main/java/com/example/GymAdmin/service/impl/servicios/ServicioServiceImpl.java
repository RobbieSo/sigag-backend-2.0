package com.example.GymAdmin.service.impl.servicios;


import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.servicio.ServicioRequest;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.ServicioEntity;
import com.example.GymAdmin.repository.IServicioRepository;
import com.example.GymAdmin.service.IServicioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioServiceImpl implements IServicioService {

    private final IServicioRepository iServicioRepository;

    public ServicioServiceImpl(IServicioRepository iServicioRepository) {
        this.iServicioRepository = iServicioRepository;
    }

    @Override
    public ServicioResponse create(ServicioRequest request) {
        ServicioEntity servicioEntity = new ServicioEntity();
        servicioEntity.setNombreServicio(request.getNombreServicio());
        servicioEntity.setDescripcion(request.getDescripcion());
        servicioEntity.setEstatus(request.getEstatus());
        servicioEntity.setCreatedAtd(LocalDateTime.now());
        servicioEntity = iServicioRepository.save(servicioEntity);

        ServicioResponse response = new ServicioResponse();
        response.setIdServicio(servicioEntity.getIdServicio());
        response.setCreatedAt(servicioEntity.getCreatedAtd().toString());
        response.setDescripcion(servicioEntity.getDescripcion());
        response.setEstatus(servicioEntity.getEstatus());
        response.setNombreServicio(servicioEntity.getNombreServicio());

        return response;
    }

    @Override
    public ServicioResponse find(Integer integer) {
        ServicioEntity servicioEntity = iServicioRepository.findById(integer).get();//implment optional
        ServicioResponse response = new ServicioResponse();
        response.setIdServicio(servicioEntity.getIdServicio());
        response.setCreatedAt(servicioEntity.getCreatedAtd().toString());
        response.setDescripcion(servicioEntity.getDescripcion());
        response.setEstatus(servicioEntity.getEstatus());
        response.setNombreServicio(servicioEntity.getNombreServicio());
        return response;
    }

    @Override
    public List<ServicioResponse> findAll() {

        List<ServicioEntity> listaServicios = iServicioRepository.findAll();
        List<ServicioResponse> listaServiciosRes = new ArrayList<>();

        for(ServicioEntity servicioEntity : listaServicios ){
            ServicioResponse response = new ServicioResponse();
            response.setIdServicio(servicioEntity.getIdServicio());
            response.setCreatedAt(servicioEntity.getCreatedAtd().toString());
            response.setDescripcion(servicioEntity.getDescripcion());
            response.setEstatus(servicioEntity.getEstatus());
            response.setNombreServicio(servicioEntity.getNombreServicio());
            listaServiciosRes.add(response);
        }
        return listaServiciosRes;
    }

    @Override
    public ServicioResponse update(ServicioRequest request, Integer integer) {
        ServicioEntity servicioEntity = iServicioRepository.findById(integer).get();
        servicioEntity.setIdServicio(request.getIdServicio());
        servicioEntity.setNombreServicio(request.getNombreServicio());
        servicioEntity.setDescripcion(request.getDescripcion());
        servicioEntity.setEstatus(request.getEstatus());
        //servicioEntity.setCreatedAtd(LocalDateTime.now());
        servicioEntity = iServicioRepository.save(servicioEntity);

        ServicioResponse response = new ServicioResponse();
        response.setIdServicio(servicioEntity.getIdServicio());
        response.setCreatedAt(servicioEntity.getCreatedAtd().toString());
        response.setDescripcion(servicioEntity.getDescripcion());
        response.setEstatus(servicioEntity.getEstatus());
        response.setNombreServicio(servicioEntity.getNombreServicio());

        return response;
    }

    @Override
    public void delete(Integer integer) {
        iServicioRepository.findById(integer).ifPresent(iServicioRepository::delete);

    }
}

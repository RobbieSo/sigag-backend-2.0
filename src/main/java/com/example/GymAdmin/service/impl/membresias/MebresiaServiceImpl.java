package com.example.GymAdmin.service.impl.membresias;


import com.example.GymAdmin.dto.membresia.MembresiaRequest;
import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.entity.MembresiaEntity;
import com.example.GymAdmin.entity.ServicioEntity;
import com.example.GymAdmin.repository.IMembresiaRepository;
import com.example.GymAdmin.repository.IServicioRepository;
import com.example.GymAdmin.service.IMembresiaService;
import com.example.GymAdmin.util.GeneralUtilities;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MebresiaServiceImpl  implements IMembresiaService {

    private final IMembresiaRepository iMembresiaRepository;
    private final GeneralUtilities generalUtilities;

    private final IServicioRepository iServicioRepository;

    public MebresiaServiceImpl(IMembresiaRepository iMembresiaRepository, GeneralUtilities generalUtilities, IServicioRepository iServicioRepository) {
        this.iMembresiaRepository = iMembresiaRepository;
        this.generalUtilities = generalUtilities;
        this.iServicioRepository = iServicioRepository;
    }

    @Override
    public MembresiaResponse create(MembresiaRequest request) {
        MembresiaEntity member = new MembresiaEntity();
        member.setTipo(request.getTipo());
        member.setPlan(request.getPlan());
        member.setHorario(request.getHorario());
        member.setCondiciones(request.getCondiciones());
        member.setEntrenamientoPersonalizado(request.getEntrenamientoPersonalizado());
        member.setVigencia(generalUtilities.calculateVigencia(request.getPlan(), request.getFechainicio()));
        member.setFechainicio(request.getFechainicio());
        member.setDescuento(request.getDescuento());
        member.setAsesoria(request.getAsesoria());
        member.setEstatus(request.getEstatus());
        if(!request.getIdsServicios().isEmpty()){
            for(Integer sub : request.getIdsServicios()){
                ServicioEntity servicioExistente = iServicioRepository.findById(sub)
                        .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
                // Agregar el servicio a la membresía
                member.getServicios().add(servicioExistente);
                // Mantener la bidireccionalidad: agregar la membresía al servicio
                servicioExistente.getMembresias().add(member);
            }
        }


        member=   iMembresiaRepository.save(member);
        MembresiaResponse response = getMembresiaResponse(member);

        if(!member.getServicios().isEmpty()){
            List<ServicioResponse> services = getServicioResponses(member);
            response.setServicios(services);
        }

        return response;
    }

    public static MembresiaResponse getMembresiaResponse(MembresiaEntity member) {
        MembresiaResponse response = new MembresiaResponse();
        response.setIdMembresia(member.getIdMembresia());
        response.setTipo(member.getTipo());
        response.setPlan(member.getPlan());
        response.setHorario(member.getHorario());
        response.setCondiciones(member.getCondiciones());
        response.setEntrenamientoPersonalizado(member.getEntrenamientoPersonalizado());
        response.setVigencia(member.getVigencia().toString());
        response.setFechainicio(member.getFechainicio().toString());
        response.setDescuento(member.getDescuento());
        response.setAsesoria(member.getAsesoria());
        response.setEstatus(member.getEstatus());
        return response;
    }

    public static List<ServicioResponse> getServicioResponses(MembresiaEntity member) {
        List<ServicioResponse> services =  new ArrayList<>();
        for(ServicioEntity sub : member.getServicios() ){
           ServicioResponse ser = new ServicioResponse();
            ser.setIdServicio(sub.getIdServicio());
            ser.setCreatedAt(sub.getCreatedAtd().toString());
            ser.setDescripcion(sub.getDescripcion());
            ser.setEstatus(sub.getEstatus());
            ser.setNombreServicio(sub.getNombreServicio());
            services.add(ser);

        }
        return services;
    }

    @Override
    public MembresiaResponse find(Integer integer) {
       MembresiaEntity member= iMembresiaRepository.findById(integer).get();
        MembresiaResponse response = getMembresiaResponse(member);
        if(!member.getServicios().isEmpty()){
            List<ServicioResponse> services = getServicioResponses(member);
            response.setServicios(services);
        }
        return response;
    }

    @Override
    public List<MembresiaResponse> findAll() {
        List<MembresiaResponse> listReponse = new ArrayList<>();
        List<MembresiaEntity> listMemberEntity = iMembresiaRepository.findAll();
        for(MembresiaEntity sub : listMemberEntity){
            MembresiaResponse response = getMembresiaResponse(sub);
            if(!sub.getServicios().isEmpty()){
                List<ServicioResponse> services = getServicioResponses(sub);
                response.setServicios(services);
            }
            listReponse.add(response);
        }
        return listReponse;
    }

    @Override
    public MembresiaResponse update(MembresiaRequest request, Integer integer) {
        // Buscar la membresía existente
        MembresiaEntity member = iMembresiaRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));

        // Actualizar los campos de la membresía
        member.setTipo(request.getTipo());
        member.setPlan(request.getPlan());
        member.setHorario(request.getHorario());
        member.setCondiciones(request.getCondiciones());
        member.setEntrenamientoPersonalizado(request.getEntrenamientoPersonalizado());
        member.setVigencia(generalUtilities.calculateVigencia(request.getPlan(), request.getFechainicio()));
        member.setFechainicio(request.getFechainicio());
        member.setDescuento(request.getDescuento());
        member.setAsesoria(request.getAsesoria());
        member.setEstatus(request.getEstatus());

        // Actualizar la relación con los servicios
        if (!request.getIdsServicios().isEmpty()) {
            // Limpiar servicios actuales para evitar duplicados y problemas de referencia
            for (ServicioEntity servicio : member.getServicios()) {
                servicio.getMembresias().remove(member);
            }
            member.getServicios().clear();

            // Agregar los nuevos servicios
            for (Integer sub : request.getIdsServicios()) {
                ServicioEntity servicioExistente = iServicioRepository.findById(sub)
                        .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
                member.getServicios().add(servicioExistente);
                servicioExistente.getMembresias().add(member);
            }
        } else {
            // Si no hay servicios en la solicitud, eliminar los actuales
            for (ServicioEntity servicio : member.getServicios()) {
                servicio.getMembresias().remove(member);
            }
            member.getServicios().clear();
        }

        // Guardar la membresía actualizada
        member = iMembresiaRepository.save(member);

        // Construir la respuesta
        MembresiaResponse response = getMembresiaResponse(member);

        if (!member.getServicios().isEmpty()) {
            List<ServicioResponse> services = getServicioResponses(member);
            response.setServicios(services);
        }

        return response;
    }

    @Override
    public void delete(Integer id) {
        // Buscar la membresía existente
        MembresiaEntity member = iMembresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));

        // Eliminar la relación con los servicios antes de borrar la membresía
        for (ServicioEntity servicio : member.getServicios()) {
            servicio.getMembresias().remove(member);
        }
        member.getServicios().clear();

        // Eliminar la membresía
        iMembresiaRepository.delete(member);
    }
}

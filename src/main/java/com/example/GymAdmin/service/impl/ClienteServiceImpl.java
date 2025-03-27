package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.request.AsistenciaRequest;
import com.example.GymAdmin.dto.request.AsistenciaResponseCr;
import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.AsistenciaResponse;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.response.ContratoResponse;
import com.example.GymAdmin.entity.AsistenciaEntity;
import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.ContratoEntity;
import com.example.GymAdmin.entity.PersonaEntity;
import com.example.GymAdmin.repository.IAsistenciaRepository;
import com.example.GymAdmin.repository.IClienteRepository;
import com.example.GymAdmin.repository.IContratoRepository;
import com.example.GymAdmin.repository.IPersonaRepository;
import com.example.GymAdmin.service.IClienteService;
import com.example.GymAdmin.service.IContratoService;
import com.example.GymAdmin.service.IMembresiaService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.GymAdmin.util.MmebresiaUtil.calculateAdd;
import static com.example.GymAdmin.util.MmebresiaUtil.validateAssis;


@Service
@Transactional
public class ClienteServiceImpl implements IClienteService{

    private final IPersonaRepository iPersonaRepository;
    private final IClienteRepository iClienteRepository;
    private final IContratoService iContratoService;

    private final IMembresiaService iMembresiaService;

    private final IContratoRepository iContratoRepository;
    private final IAsistenciaRepository iAsistenciaRepository;


    public ClienteServiceImpl(IPersonaRepository iPersonaRepository, IClienteRepository iClienteRepository, IContratoService iContratoService, IMembresiaService iMembresiaService, IContratoRepository iContratoRepository, IAsistenciaRepository iAsistenciaRepository) {
        this.iPersonaRepository = iPersonaRepository;
        this.iClienteRepository = iClienteRepository;
        this.iContratoService = iContratoService;
        this.iMembresiaService = iMembresiaService;
        this.iContratoRepository = iContratoRepository;
        this.iAsistenciaRepository = iAsistenciaRepository;
    }

    @Override
    public ClienteResponse create(ClienteRequest request) {
        // Mapear PersonaEntity y guardar
        PersonaEntity persona = new PersonaEntity();
        persona.setNombre(request.getNombre());
        persona.setApellidoPaterno(request.getApellidoPaterno());
        persona.setApellidoMaterno(request.getApellidoMaterno());
        persona.setCorreo(request.getCorreo());
        persona.setTelefono(request.getTelefono());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona = iPersonaRepository.save(persona);

        // Mapear ClienteEntity, asignar persona y guardar
        ClienteEntity cliente = new ClienteEntity();
        cliente.setPersona(persona);
        cliente = iClienteRepository.save(cliente);


        ClienteResponse clienteResponse = getClienteResponse(cliente);
        return clienteResponse;
    }

    public static ClienteResponse getClienteResponse(ClienteEntity cliente) {
        ClienteResponse clienteResponse= new ClienteResponse();

        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNombre(cliente.getPersona().getNombre());
        clienteResponse.setApellidoPaterno(cliente.getPersona().getApellidoPaterno());
        clienteResponse.setApellidoMaterno(cliente.getPersona().getApellidoMaterno());
        clienteResponse.setCorreo(cliente.getPersona().getCorreo());
        clienteResponse.setTelefono(cliente.getPersona().getTelefono());
        clienteResponse.setFechaNacimiento(cliente.getPersona().getFechaNacimiento());
        return clienteResponse;
    }

    @Override
    public ClienteResponse find(Integer integer) {
        ClienteEntity cliente = iClienteRepository.findById(integer).get();
        // Mapear ClienteResponse y devolver
        ClienteResponse clienteResponse= new ClienteResponse();
        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNombre(cliente.getPersona().getNombre());
        clienteResponse.setApellidoPaterno(cliente.getPersona().getApellidoPaterno());
        clienteResponse.setApellidoMaterno(cliente.getPersona().getApellidoMaterno());
        clienteResponse.setCorreo(cliente.getPersona().getCorreo());
        clienteResponse.setTelefono(cliente.getPersona().getTelefono());
        clienteResponse.setFechaNacimiento(cliente.getPersona().getFechaNacimiento());
        return clienteResponse;
    }

    @Override
    public List<ClienteResponse> findAll() {

        List<ClienteEntity> listCliente = iClienteRepository.findAll();
        List<ClienteResponse> listClienteResponse = new ArrayList<>();

        for(ClienteEntity cliente : listCliente ){
            ClienteResponse clienteResponse= new ClienteResponse();
            clienteResponse.setIdCliente(cliente.getIdCliente());
            clienteResponse.setNombre(cliente.getPersona().getNombre());
            clienteResponse.setApellidoPaterno(cliente.getPersona().getApellidoPaterno());
            clienteResponse.setApellidoMaterno(cliente.getPersona().getApellidoMaterno());
            clienteResponse.setCorreo(cliente.getPersona().getCorreo());
            clienteResponse.setTelefono(cliente.getPersona().getTelefono());
            clienteResponse.setFechaNacimiento(cliente.getPersona().getFechaNacimiento());
            listClienteResponse.add(clienteResponse);
        }

        return listClienteResponse;
    }

    @Override
    public ClienteResponse update(ClienteRequest request, Integer integer) {

        ClienteEntity  clienteEntity = iClienteRepository.findById(integer).get();
        PersonaEntity personaExistente = clienteEntity.getPersona();

        personaExistente.setNombre(request.getNombre());
        personaExistente.setApellidoPaterno(request.getApellidoPaterno());
        personaExistente.setApellidoMaterno(request.getApellidoMaterno());
        personaExistente.setFechaNacimiento(request.getFechaNacimiento());
        personaExistente.setCorreo(request.getCorreo());
        personaExistente.setTelefono(request.getTelefono());

        iPersonaRepository.save(personaExistente);

        clienteEntity.setIdCliente(integer);
        clienteEntity.setPersona(personaExistente);

        clienteEntity = iClienteRepository.save(clienteEntity);

        ClienteResponse clienteResponse= new ClienteResponse();
        clienteResponse.setIdCliente(clienteEntity.getIdCliente());
        clienteResponse.setNombre(clienteEntity.getPersona().getNombre());
        clienteResponse.setApellidoPaterno(clienteEntity.getPersona().getApellidoPaterno());
        clienteResponse.setApellidoMaterno(clienteEntity.getPersona().getApellidoMaterno());
        clienteResponse.setCorreo(clienteEntity.getPersona().getCorreo());
        clienteResponse.setTelefono(clienteEntity.getPersona().getTelefono());
        clienteResponse.setFechaNacimiento(clienteEntity.getPersona().getFechaNacimiento());

        return clienteResponse;
    }

    @Override
    public void delete(Integer integer) {

        iClienteRepository.findById(integer).ifPresent(cliente -> {
            iClienteRepository.delete(cliente);
            iPersonaRepository.delete(cliente.getPersona()); // También eliminamos la persona
        });

    }

    @Override
    public List<ClienteResponse> getClientesPorVencer() {
        List<ClienteResponse> listaCliente = new ArrayList<>();
        List<ContratoResponse> listaContrato = iContratoService.findAll();
        List<MembresiaResponse> listaMembresia = iMembresiaService.findAll();
        for(ContratoResponse sub: listaContrato){
            if(calculateAdd(sub.getMembresia())){
                listaCliente.add(sub.getCliente());
            }

        }
        return listaCliente;
    }

    @Override
    public AsistenciaResponse getRegistraAsistencia(AsistenciaRequest asistenciaRequest) {
        ClienteEntity cliente = iClienteRepository.getIdValido(asistenciaRequest.getCorreo());
        AsistenciaResponse response = AsistenciaResponse.builder().build();
        ContratoEntity contratoEntity = new ContratoEntity();
        if(iContratoRepository.findByCliente(cliente).isEmpty()){
            response.setEstado("Membresia no valida");
            response.setMensaje("NO SE ENCUENTRA CONTRATO ASOCIADO");// TODO la buena practica dice arrojar una exepcion de negocio a un handler de exepciones
            return response;
        }
        contratoEntity = iContratoRepository.findByCliente(cliente).get();
        if(validateAssis(contratoEntity.getMembresia())){
             iAsistenciaRepository.save( AsistenciaEntity.builder()
                    .cliente(contratoEntity.getCliente())
                    .fechaHoraRegistro(LocalDateTime.now())
                    .build());
            response.setEstado("Membresia valida");
            response.setMensaje("Asistencia registrada correctamente");
        }else{
            response.setEstado("Membresia no valida");
            response.setMensaje("NO SE REGISTRA ASISTENCIA");
        }

        return response;
    }

    @Override
    public List<AsistenciaResponseCr> getAsistenciasByCliente(Integer idCliente) {
        List<AsistenciaResponseCr> asistenciaResponseCrs = new ArrayList<>();
        ClienteEntity clienteEntity = iClienteRepository.getReferenceById(idCliente);
         for(AsistenciaEntity sub : clienteEntity.getAsistenciaEntities()){
             asistenciaResponseCrs.add(AsistenciaResponseCr.builder()
                             .idAsistencia(sub.getIdAsistencia())
                             .fechaHoraRegistro(sub.getFechaHoraRegistro().toString())
                     .build());
         }
        return asistenciaResponseCrs;
    }


}

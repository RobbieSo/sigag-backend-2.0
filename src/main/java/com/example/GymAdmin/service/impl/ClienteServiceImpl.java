package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.PersonaEntity;
import com.example.GymAdmin.repository.IClienteRepository;
import com.example.GymAdmin.repository.IPersonaRepository;
import com.example.GymAdmin.service.IClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ClienteServiceImpl implements IClienteService{

    private final IPersonaRepository iPersonaRepository;
    private final IClienteRepository iClienteRepository;

    public ClienteServiceImpl(IPersonaRepository iPersonaRepository, IClienteRepository iClienteRepository) {
        this.iPersonaRepository = iPersonaRepository;
        this.iClienteRepository = iClienteRepository;
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
}

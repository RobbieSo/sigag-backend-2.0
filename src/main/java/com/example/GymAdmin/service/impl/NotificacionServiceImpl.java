package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.request.NotificacionRequest;
import com.example.GymAdmin.dto.response.NotificacionResponse;
import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.NotificacionEntity;
import com.example.GymAdmin.entity.PersonaEntity;
import com.example.GymAdmin.exeption.ClienteNotFoundException;
import com.example.GymAdmin.repository.IClienteRepository;
import com.example.GymAdmin.repository.INotificacionRespository;
import com.example.GymAdmin.service.INotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class NotificacionServiceImpl implements INotificacionService {

    private final IClienteRepository  iClienteRepository;

    private final JavaMailSender mailSender;
    private  final INotificacionRespository iNotificacionRespository;
    public NotificacionServiceImpl(IClienteRepository iClienteRepository, JavaMailSender mailSender, INotificacionRespository iNotificacionRespository) {
        this.iClienteRepository = iClienteRepository;
        this.mailSender = mailSender;
        this.iNotificacionRespository = iNotificacionRespository;
    }

    @Override
    public NotificacionResponse create(NotificacionRequest request) {
        if (!iClienteRepository.existsById(request.getIdCliente())) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }
        String correo = iClienteRepository.findById(request.getIdCliente()).get().getPersona().getCorreo();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin_aresgym@aresgym.site");
        message.setTo(correo);
        message.setSubject(request.getAsunto());
        message.setText(request.getMensaje());
        mailSender.send(message);
        ClienteEntity cliente = iClienteRepository.findById(request.getIdCliente()).get();

        NotificacionEntity notificacionEntity = new NotificacionEntity();
        notificacionEntity.setCliente(cliente);
        notificacionEntity.setAsunto(request.getAsunto());
        notificacionEntity.setMensaje(request.getMensaje());
        notificacionEntity.setFechaEnvio(LocalDate.now());
        notificacionEntity = iNotificacionRespository.save(notificacionEntity);

        NotificacionResponse response = new NotificacionResponse();
        response.setIdNotificacion(notificacionEntity.getIdnotificacion());
        response.setIdCliente(cliente.getIdCliente());
        response.setAsunto(notificacionEntity.getAsunto());
        response.setMensaje(notificacionEntity.getMensaje());
        response.setFechaEnvio(notificacionEntity.getFechaEnvio().toString());

        return response;
    }

    @Override
    public NotificacionResponse find(Integer integer) {
        return null;
    }

    @Override
    public List<NotificacionResponse> findAll() {
        return null;
    }

    @Override
    public NotificacionResponse update(NotificacionRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}

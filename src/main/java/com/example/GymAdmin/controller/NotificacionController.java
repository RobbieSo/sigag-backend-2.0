package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.request.NotificacionRequest;
import com.example.GymAdmin.dto.request.PagoRequest;
import com.example.GymAdmin.dto.response.NotificacionResponse;
import com.example.GymAdmin.dto.response.PagoResponse;
import com.example.GymAdmin.service.INotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "notificacion")
public class NotificacionController {

    private final INotificacionService iNotificacionService;

    public NotificacionController(INotificacionService iNotificacionService) {
        this.iNotificacionService = iNotificacionService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<NotificacionResponse> crearContrato(@RequestBody NotificacionRequest notificacionRequest){
        NotificacionResponse prueba = iNotificacionService.create(notificacionRequest);
        return ResponseEntity.ok(prueba);
    }
}

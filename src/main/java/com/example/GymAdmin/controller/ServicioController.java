package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.servicio.ServicioRequest;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.service.IClienteService;
import com.example.GymAdmin.service.IServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin
@RequestMapping(path = "servicio")
public class ServicioController {

    private final IServicioService iServicioService;

    public ServicioController(IServicioService iServicioService) {
        this.iServicioService = iServicioService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<ServicioResponse> crearServicio(@RequestBody ServicioRequest servicioRequest){
        ServicioResponse prueba = iServicioService.create(servicioRequest);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/obtenerPorId")
    public ResponseEntity<ServicioResponse> buscarPorId(@RequestParam("id") Integer id){
        ServicioResponse prueba = iServicioService.find(id);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaServicios")
    public ResponseEntity<List<ServicioResponse>> buscarServicios(){
        List<ServicioResponse> prueba = iServicioService.findAll();
        return ResponseEntity.ok(prueba);
    }


}

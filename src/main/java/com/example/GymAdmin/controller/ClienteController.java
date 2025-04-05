package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.request.AsistenciaRequest;

import com.example.GymAdmin.dto.request.AsistenciaResponseCr;
import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.AsistenciaResponse;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.service.IClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "cliente")
public class ClienteController {

    private final IClienteService iClienteService;

    public ClienteController(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest pedidoRequest){
        ClienteResponse prueba = iClienteService.create(pedidoRequest);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/obtenerPorId")
    public ResponseEntity<ClienteResponse> buscarPorId(@RequestParam("id") Integer id){
        ClienteResponse prueba = iClienteService.find(id);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaClientes")
    public ResponseEntity<List<ClienteResponse>> buscarClientes(){
        List<ClienteResponse> prueba = iClienteService.findAll();
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaClientesPorVencer")
    public ResponseEntity<List<ClienteResponse>> buscarClientesporvencer(){
        List<ClienteResponse> prueba = iClienteService.getClientesPorVencer();
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @PostMapping(value ="/registrarAsistencia")
    public ResponseEntity<AsistenciaResponse> verificarAsistencia(@RequestBody AsistenciaRequest asistenciaRequest){
        AsistenciaResponse prueba = iClienteService.getRegistraAsistencia(asistenciaRequest);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaAsistenciaPorCliente")
    public ResponseEntity<List<AsistenciaResponseCr>> buscarAsistenciasdeClientes(@RequestParam("id") Integer id){
        List<AsistenciaResponseCr> prueba = iClienteService.getAsistenciasByCliente(id);
        return ResponseEntity.ok(prueba);
    }




}

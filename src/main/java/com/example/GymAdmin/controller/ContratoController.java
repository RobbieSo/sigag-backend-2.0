package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.membresia.MembresiaRequest;
import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.request.ContratoRequest;
import com.example.GymAdmin.dto.response.ContratoResponse;
import com.example.GymAdmin.service.IContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "contrato")
public class ContratoController {

    private final IContratoService iContratoService;

    public ContratoController(IContratoService iContratoService) {
        this.iContratoService = iContratoService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<ContratoResponse> crearContrato(@RequestBody ContratoRequest contratoRequest){
        ContratoResponse prueba = iContratoService.create(contratoRequest);
        return ResponseEntity.ok(prueba);
    }


    @ResponseBody
    @GetMapping(value ="/obtenerPorId")
    public ResponseEntity<ContratoResponse> buscarPorId(@RequestParam("id") Integer id){
        ContratoResponse prueba = iContratoService.find(id);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaContratos")
    public ResponseEntity<List<ContratoResponse>> buscarMembresias(){
        List<ContratoResponse> prueba = iContratoService.findAll();
        return ResponseEntity.ok(prueba);
    }


}

package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.request.ContratoRequest;
import com.example.GymAdmin.dto.request.PagoRequest;
import com.example.GymAdmin.dto.response.ContratoResponse;
import com.example.GymAdmin.dto.response.PagoResponse;
import com.example.GymAdmin.service.IContratoService;
import com.example.GymAdmin.service.IPagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pago")
public class PagoController {
    private final IPagoService iPagoService;

    public PagoController(IPagoService iPagoService) {
        this.iPagoService = iPagoService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<PagoResponse> crearContrato(@RequestBody PagoRequest pagoRequest){
        PagoResponse prueba = iPagoService.create(pagoRequest);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/obtenerPorId")
    public ResponseEntity<PagoResponse> buscarPorId(@RequestParam("id") Integer id){
        PagoResponse prueba = iPagoService.find(id);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaPagos")
    public ResponseEntity<List<PagoResponse>> buscarPagos(){
        List<PagoResponse> prueba = iPagoService.findAll();
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @PostMapping(value ="/actualizar")
    public ResponseEntity<PagoResponse> actualizarPago(@RequestBody PagoRequest pagoRequest){
        PagoResponse prueba = iPagoService.update(pagoRequest, pagoRequest.getIdPago());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
        iPagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

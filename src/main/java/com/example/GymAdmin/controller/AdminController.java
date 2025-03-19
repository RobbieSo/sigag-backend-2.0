package com.example.GymAdmin.controller;


import com.example.GymAdmin.dto.membresia.MembresiaRequest;
import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.request.ContratoRequest;
import com.example.GymAdmin.dto.request.PagoRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.response.ContratoResponse;
import com.example.GymAdmin.dto.response.PagoResponse;
import com.example.GymAdmin.dto.servicio.ServicioRequest;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "admin")
public class AdminController {

    private final IClienteService iClienteService;
    private final IContratoService iContratoService;

    private final IMembresiaService iMembresiaService;
    private final IPagoService iPagoService;

    private final IServicioService iServicioService;


    public AdminController(IClienteService iClienteService, IContratoService iContratoService, IMembresiaService iMembresiaService, IPagoService iPagoService, IServicioService iServicioService) {
        this.iClienteService = iClienteService;
        this.iContratoService = iContratoService;
        this.iMembresiaService = iMembresiaService;
        this.iPagoService = iPagoService;
        this.iServicioService = iServicioService;
    }

    @ResponseBody
    @PostMapping(value ="cliente/actualizar")
    public ResponseEntity<ClienteResponse> actualizarCliente(@RequestBody ClienteRequest pedidoRequest){
        ClienteResponse prueba = iClienteService.update(pedidoRequest, pedidoRequest.getIdCliente());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        iClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ResponseBody
    @PostMapping(value ="contrato/actualizar")
    public ResponseEntity<ContratoResponse> actualizarContrato(@RequestBody ContratoRequest contratoRequest){
        ContratoResponse prueba = iContratoService.update(contratoRequest, contratoRequest.getIdContrato());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("contrato/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Integer id) {
        iContratoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PostMapping(value ="pago/actualizar")
    public ResponseEntity<PagoResponse> actualizarPago(@RequestBody PagoRequest pagoRequest){
        PagoResponse prueba = iPagoService.update(pagoRequest, pagoRequest.getIdPago());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("pago/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
        iPagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PostMapping(value ="membresia/actualizar")
    public ResponseEntity<MembresiaResponse> actualizarMembresia(@RequestBody MembresiaRequest membresiaRequest){
        MembresiaResponse prueba = iMembresiaService.update(membresiaRequest, membresiaRequest.getIdMembresia());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("membresia/{id}")
    public ResponseEntity<Void> eliminarMembresia(@PathVariable Integer id) {
        iMembresiaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PostMapping(value ="servicio/actualizar")
    public ResponseEntity<ServicioResponse> actualizarServicio(@RequestBody ServicioRequest servicioRequest){
        ServicioResponse prueba = iServicioService.update(servicioRequest  , servicioRequest.getIdServicio());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("servicio/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Integer id) {
        iServicioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

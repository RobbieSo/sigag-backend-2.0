package com.example.GymAdmin.controller;

import com.example.GymAdmin.dto.membresia.MembresiaRequest;
import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import com.example.GymAdmin.dto.request.ClienteRequest;
import com.example.GymAdmin.dto.response.ClienteResponse;
import com.example.GymAdmin.dto.servicio.ServicioRequest;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import com.example.GymAdmin.service.IMembresiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5000/")
@RestController
@RequestMapping(path = "membresia")
public class MembresiaController {

    private final IMembresiaService iMembresiaService;

    public MembresiaController(IMembresiaService iMembresiaService) {
        this.iMembresiaService = iMembresiaService;
    }

    @ResponseBody
    @PostMapping(value ="/crear")
    public ResponseEntity<MembresiaResponse> crearMembresia(@RequestBody MembresiaRequest membresiaRequest){
        MembresiaResponse prueba = iMembresiaService.create(membresiaRequest);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/obtenerPorId")
    public ResponseEntity<MembresiaResponse> buscarPorId(@RequestParam("id") Integer id){
        MembresiaResponse prueba = iMembresiaService.find(id);
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @GetMapping(value ="/listaMembresias")
    public ResponseEntity<List<MembresiaResponse>> buscarMembresias(){
        List<MembresiaResponse> prueba = iMembresiaService.findAll();
        return ResponseEntity.ok(prueba);
    }

    @ResponseBody
    @PostMapping(value ="/actualizar")
    public ResponseEntity<MembresiaResponse> actualizarMembresia(@RequestBody MembresiaRequest membresiaRequest){
        MembresiaResponse prueba = iMembresiaService.update(membresiaRequest, membresiaRequest.getIdMembresia());
        return ResponseEntity.ok(prueba);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMembresia(@PathVariable Integer id) {
        iMembresiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

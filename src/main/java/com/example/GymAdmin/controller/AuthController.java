package com.example.GymAdmin.controller;

import com.example.GymAdmin.dto.RecuperacionDto;
import com.example.GymAdmin.dto.request.LoginRequest;
import com.example.GymAdmin.dto.request.RegisterRequest;
import com.example.GymAdmin.dto.response.AuthResponse;
import com.example.GymAdmin.entity.PersonaEntity;
import com.example.GymAdmin.entity.RecuperacionEntity;
import com.example.GymAdmin.entity.Role;
import com.example.GymAdmin.entity.User;
import com.example.GymAdmin.repository.IPersonaRepository;
import com.example.GymAdmin.repository.IRecuperacionRepository;
import com.example.GymAdmin.repository.RoleRepository;
import com.example.GymAdmin.repository.UserRepository;
import com.example.GymAdmin.service.ITwilioServicePhone;
import com.example.GymAdmin.service.impl.IRecuperacionService;
import com.example.GymAdmin.service.impl.JwtBlacklistService;
import com.example.GymAdmin.util.JwtUtil;
import com.example.GymAdmin.util.RoleName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:5000/")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final IPersonaRepository iPersonaRepository;

    private final ITwilioServicePhone iTwilioServicePhone;

    private final JavaMailSender mailSender;

    private final IRecuperacionService iRecuperacionService;

    private final IRecuperacionRepository iRecuperacionRepository;


    private final JwtBlacklistService jwtBlacklistService; // 🔹 Servicio para invalidar tokens

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder, IPersonaRepository iPersonaRepository, ITwilioServicePhone iTwilioServicePhone, JavaMailSender mailSender, IRecuperacionRepository iRecuperacionRepository, IRecuperacionService iRecuperacionService,
            JwtBlacklistService jwtBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.iPersonaRepository = iPersonaRepository;
        this.iTwilioServicePhone = iTwilioServicePhone;
        this.mailSender = mailSender;
        this.iRecuperacionService = iRecuperacionService;
        this.iRecuperacionRepository = iRecuperacionRepository;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    // 🔐 LOGIN DE USUARIO
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    // 🟢 REGISTRO DE USUARIO
// 🟢 REGISTRO DE USUARIO CON ROLES
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }

        // 1️⃣ Crear la entidad Persona para el usuario
        PersonaEntity persona = new PersonaEntity();
        persona.setNombre("usuario de app");
        persona.setApellidoPaterno("usuario de app");
        persona.setApellidoMaterno("usuario de app");
        persona.setCorreo(request.getCorreo());
        persona.setTelefono(request.getTelefono());
        persona.setFechaNacimiento(LocalDate.now());
        persona = iPersonaRepository.save(persona);

        // 2️⃣ Generar código de recuperación
        int numeroAleatorio = new Random().nextInt(900000) + 100000;
        RecuperacionEntity recuperacionEntity = new RecuperacionEntity();
        recuperacionEntity.setCode(String.valueOf(numeroAleatorio));
        recuperacionEntity.setVigencia(LocalDateTime.now());
        recuperacionEntity.setEstatus(Boolean.FALSE);
        recuperacionEntity = iRecuperacionRepository.save(recuperacionEntity);

        // 3️⃣ Crear el usuario
        User user = new User();
        user.setPersona(persona);
        user.setRecuperacion(recuperacionEntity);
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4️⃣ Asignar roles (por defecto USER, pero permitimos que admin lo registre como ADMIN)
        Set<Role> roles = new HashSet<>();
        if (request.getRole() != null && request.getRole().equalsIgnoreCase("ADMIN")) {
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));
            roles.add(adminRole);
        } else {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
            roles.add(userRole);
        }
        user.setRoles(roles);

        // 5️⃣ Guardar usuario en la base de datos
        userRepository.save(user);

        return ResponseEntity.ok("Usuario registrado correctamente con rol: " + roles.iterator().next().getName());
    }

    // 🔴 LOGOUT DE USUARIO (INVALIDAR TOKEN)
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtBlacklistService.blacklistToken(token); // 🔹 Agregar el token a la lista negra
        }

        return ResponseEntity.ok("Logout exitoso, token invalidado.");
    }

    @PostMapping("/stepOneRecover")
    public ResponseEntity<String> stepOneRecoverver(@RequestBody RecuperacionDto recuperacionDto) {
        return iRecuperacionService.stepOneRecuperacion(recuperacionDto);
    }

    @PostMapping("/stepTwoRecover")
    public ResponseEntity<String> stepTwoRecoverver(@RequestBody RecuperacionDto recuperacionDto) {
        return iRecuperacionService.stepThowRecuperacion(recuperacionDto);
    }


}
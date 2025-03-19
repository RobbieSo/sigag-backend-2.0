package com.example.GymAdmin.service.impl;

import com.example.GymAdmin.dto.RecuperacionDto;
import com.example.GymAdmin.entity.PersonaEntity;
import com.example.GymAdmin.entity.RecuperacionEntity;
import com.example.GymAdmin.entity.User;
import com.example.GymAdmin.repository.IPersonaRepository;
import com.example.GymAdmin.repository.IRecuperacionRepository;
import com.example.GymAdmin.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class RecuperacionServiceImpl implements  IRecuperacionService{

    private final UserRepository userRepository;
    private final IRecuperacionRepository iRecuperacionRepository;
    private final IPersonaRepository iPersonaRepository;

    private final JavaMailSender mailSender;

    private final PasswordEncoder passwordEncoder;

    public RecuperacionServiceImpl(UserRepository userRepository, com.example.GymAdmin.repository.IRecuperacionRepository iRecuperacionRepository, com.example.GymAdmin.repository.IPersonaRepository iPersonaRepository, IRecuperacionRepository iRecuperacionRepository1, IPersonaRepository iPersonaRepository1, JavaMailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.iRecuperacionRepository = iRecuperacionRepository1;
        this.iPersonaRepository = iPersonaRepository1;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> stepOneRecuperacion(RecuperacionDto recuperacionDto) {
        if (!userRepository.findByUsername(recuperacionDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        PersonaEntity per  = userRepository.findByUsername(recuperacionDto.getUsername()).get().getPersona();
        if (!per.getCorreo().equals(recuperacionDto.getCorreo())) {
            return ResponseEntity.badRequest().body("El correo no existe o no esta aosciado correctamente");
        }
        User us = userRepository.findByUsername(recuperacionDto.getUsername()).get();
        RecuperacionEntity recu = us.getRecuperacion();
        recu.setEstatus(Boolean.TRUE);
        Random random = new Random();
        int numeroAleatorio = random.nextInt(900000) + 100000;
        recu.setCode(String.valueOf(numeroAleatorio));
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime diezMinutosDespues = ahora.plusMinutes(11);
        recu.setVigencia(diezMinutosDespues);
        recu = iRecuperacionRepository.save(recu);
        us= userRepository.save(us);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin_aresgym@aresgym.site");
        message.setTo(recuperacionDto.getCorreo());
        message.setSubject("CODIGO DE VERIFICACION");
        message.setText("Tu código de recuperación: "+ recu.getCode());
        mailSender.send(message);



        return ResponseEntity.ok("Se envio un correo con un codigo de recuperacion");
    }

    @Override
    public ResponseEntity<String> stepThowRecuperacion(RecuperacionDto recuperacionDto) {
        User us = userRepository.findByUsername(recuperacionDto.getUsername()).get();

        if(!us.getRecuperacion().getEstatus()){
            return ResponseEntity.badRequest().body("El usuario no cuenta con estatus de recuperacion");
        }
        if(!us.getRecuperacion().getCode().equals(recuperacionDto.getCode())){
            return ResponseEntity.badRequest().body("Codigo de recuperacion no valido");
        }

        if(us.getRecuperacion().getVigencia().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("Codigo expirado");
        }
        RecuperacionEntity rec = us.getRecuperacion();
        rec.setEstatus(Boolean.FALSE);
        us.setPassword(passwordEncoder.encode(recuperacionDto.getPasswordUpdate()));

        rec=iRecuperacionRepository.save(rec);
        us.setRecuperacion(rec);
        userRepository.save(us);



        return ResponseEntity.ok("Se actualizo la contraseña correctamente");
    }
}

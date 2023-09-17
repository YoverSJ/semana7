package com.codigo.semana7.infraestructure.controller;

import com.codigo.semana7.application.service.UsuarioService;
import com.codigo.semana7.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario crearUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(crearUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id){
        return usuarioService.getUsuarioById(id).map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping()
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.actualizarUsuario(usuario).map(usuario1 -> new ResponseEntity<>(usuario1, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        if (usuarioService.eliminarUsuarioById(id)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

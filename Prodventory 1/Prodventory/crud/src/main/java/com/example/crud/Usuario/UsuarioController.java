// usuario/UsuarioController.java
package com.example.crud.Usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<UsuarioEntity> getAll() {
        return usuarioService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioEntity> create(@Valid @RequestBody UsuarioEntity usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        return usuarioService.findById(id)
                .map(u -> {
                    u.setUsername(usuario.getUsername());
                    u.setPassword(usuario.getPassword());
                    return new ResponseEntity<>(usuarioService.save(u), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

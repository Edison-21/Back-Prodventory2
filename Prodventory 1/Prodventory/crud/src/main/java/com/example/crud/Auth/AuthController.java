// auth/AuthController.java
package com.example.crud.Auth;

import com.example.crud.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return usuarioService.findByUsername(authRequest.getUsername())
                .map(user -> {
                    if (user.getPassword().equals(authRequest.getPassword())) {
                        return ResponseEntity.ok(user);
                    } else {
                        return ResponseEntity.status(401).body("Credenciales inválidas");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuario no encontrado"));
    }
}

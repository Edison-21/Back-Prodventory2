// usuario/UsuarioService.java
package com.example.crud.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioEntity> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}

package com.example.crud.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaEntity> findAll() {
        return marcaRepository.findAll();
    }

    public List<MarcaEntity> findByUsuario(Long usuarioId) {
        return marcaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<MarcaEntity> findById(Long id) {
        return marcaRepository.findById(id);
    }

    public MarcaEntity save(MarcaEntity marca) {
        return marcaRepository.save(marca);
    }

    public void delete(Long id) {
        marcaRepository.deleteById(id);
    }
}

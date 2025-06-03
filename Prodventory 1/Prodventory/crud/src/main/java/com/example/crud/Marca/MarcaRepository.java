package com.example.crud.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    List<MarcaEntity> findByUsuarioId(Long usuarioId);
}

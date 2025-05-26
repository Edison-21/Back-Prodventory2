// Categoria/CategoriaService.java
package com.example.crud.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    // Guardar una nueva categoría
    public CategoriaEntity save(CategoriaEntity entity) {
        return categoriaRepository.save(entity);
    }

    // Actualizar una categoría por ID
    public CategoriaEntity update(Long id, CategoriaEntity entity) {
        Optional<CategoriaEntity> existingCategoria = categoriaRepository.findById(id);
        if (existingCategoria.isPresent()) {
            CategoriaEntity categoriaToUpdate = existingCategoria.get();
            categoriaToUpdate.setNombre_categoria(entity.getNombre_categoria());
            // Guardar y devolver la entidad actualizada
            return categoriaRepository.save(categoriaToUpdate);
        }
        // Si no se encuentra la categoría, retornamos null
        return null;
    }


    // Obtener una categoría por ID
    public Optional<CategoriaEntity> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Eliminar una categoría por ID
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

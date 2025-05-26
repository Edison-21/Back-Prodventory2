
// Categoria/CategoriaController.java
package com.example.crud.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categorías
    @GetMapping("/")
    public ResponseEntity<List<CategoriaEntity>> findAll() {
        return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
    }
    // Guardar una nueva categoría
    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody CategoriaEntity entity, BindingResult result) {
        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        // Guardar la nueva categoría
        CategoriaEntity savedCategoria = categoriaService.save(entity);

        // Retornar un mensaje de éxito con el nombre de la categoría creada
        return new ResponseEntity<>("Categoría '" + savedCategoria.getNombre_categoria() + "' creada correctamente.", HttpStatus.CREATED);
    }


// Actualizar una categoría por ID
@PutMapping("/{id}")
public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody CategoriaEntity entity, BindingResult result) {
    // Verificar si hay errores de validación
    if (result.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    CategoriaEntity updatedCategoria = categoriaService.update(id, entity);
    
    if (updatedCategoria != null) {
        // Retornar un mensaje de éxito con el objeto actualizado
        return new ResponseEntity<>("Categoría actualizada correctamente ", HttpStatus.OK);
    } else {
        // Si no se encuentra la categoría, retornar un error con mensaje
        return new ResponseEntity<>("Categoría no encontrada para el ID proporcionado.", HttpStatus.NOT_FOUND);
    }
}


    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> findById(@PathVariable("id") Long id) {
        return categoriaService.findById(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar una categoría por ID con mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id") Long id) {
        categoriaService.deleteCategoria(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "La categoría ha sido eliminada exitosamente.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

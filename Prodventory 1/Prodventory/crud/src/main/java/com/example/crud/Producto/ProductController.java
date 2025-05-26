package com.example.crud.Producto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<List<ProductEntity>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    // Guardar un nuevo producto con validaciones
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> save(@Valid @RequestBody ProductEntity entity, BindingResult bindingResult) {
        // Verificar si hay errores de validación
        if (bindingResult.hasErrors()) {
            // Si hay errores, devolver una respuesta con los detalles del error
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    
        // Si no hay errores, guardar el producto
        productService.save(entity); // Guardar el producto en la base de datos
    
        // Respuesta con mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto guardado con éxito");
    
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    // Actualizar un producto por ID con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable("id") Long id, @Valid @RequestBody ProductEntity entity, BindingResult bindingResult) {
        // Verificar si hay errores de validación
        if (bindingResult.hasErrors()) {
            // Si hay errores, devolver una respuesta con los detalles del error
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        // Si no hay errores, actualizar el producto
        ProductEntity updatedProduct = productService.update(id, entity);
        if (updatedProduct != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto actualizado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un producto por ID con mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "El producto ha sido eliminado exitosamente.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


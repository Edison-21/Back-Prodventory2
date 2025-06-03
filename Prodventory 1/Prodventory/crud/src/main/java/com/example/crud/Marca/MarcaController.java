package com.example.crud.Marca;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/")
    public ResponseEntity<List<MarcaEntity>> findAll() {
        return new ResponseEntity<>(marcaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MarcaEntity>> findByUsuario(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(marcaService.findByUsuario(usuarioId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody MarcaEntity marca) {
        MarcaEntity nueva = marcaService.save(marca);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MarcaEntity marcaActualizada) {
        Optional<MarcaEntity> optionalMarca = marcaService.findById(id);

        if (optionalMarca.isPresent()) {
            MarcaEntity marca = optionalMarca.get();
            marca.setNombre(marcaActualizada.getNombre());
            MarcaEntity actualizada = marcaService.save(marca);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "Marca no encontrada"), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        marcaService.delete(id);
        return new ResponseEntity<>(Map.of("message", "Marca eliminada"), HttpStatus.OK);
    }
}

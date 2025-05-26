package com.example.crud.Categoria;

import com.example.crud.Producto.ProductEntity; // Importar la clase ProductEntity
import com.fasterxml.jackson.annotation.JsonManagedReference; // Importar para evitar ciclo infinito

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany; // Relación uno a muchos
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List; // Para la lista de productos

@Entity
@Data
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_categoria;

    @NotEmpty(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 20, message = "El nombre de la categoría no puede tener más de 20 caracteres")
    @Pattern(regexp = "^[^0-9]*$", message = "El nombre de la categoría no puede contener números")
    private String nombre_categoria;

    // Relación con los productos
    @OneToMany(mappedBy = "categoria") // mappedBy se refiere al campo 'categoria' en ProductEntity
    @JsonManagedReference // Evita el ciclo infinito al serializar
    private List<ProductEntity> productos;
}

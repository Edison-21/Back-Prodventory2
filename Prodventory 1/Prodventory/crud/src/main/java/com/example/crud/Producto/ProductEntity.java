package com.example.crud.Producto;

import com.example.crud.Categoria.CategoriaEntity; // Importar la clase CategoriaEntity
import com.fasterxml.jackson.annotation.JsonBackReference; // Importar para evitar ciclo infinito

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Especificar el nombre de la columna FK
import jakarta.persistence.ManyToOne; // Relación muchos a uno
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_producto; // Nombre ajustado para consistencia

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Size(max = 20, message = "El nombre del producto no puede exceder los 20 caracteres.")
    private String nombre_producto;

    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor o igual a 0.")
    private Float precio;

    @NotNull(message = "El stock no puede estar vacío.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private Integer stock;

    // Relación con la categoría
    @NotNull(message = "La categoría no puede ser nula.") // Validación adicional
    @ManyToOne(optional = false) // Relación obligatoria
    @JoinColumn(name = "categoria_id", nullable = false) // Especifica la FK y la hace NOT NULL
    @JsonBackReference // Evita el ciclo infinito al serializar
    private CategoriaEntity categoria;
}

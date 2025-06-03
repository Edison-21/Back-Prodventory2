// src/main/java/com/example/crud/Producto/dto/ProductDTO.java
package com.example.crud.Producto.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id_producto;
    private String nombre_producto;
    private Float precio;
    private Integer stock;

    private Long categoria_id;
    private String categoria_nombre;

    private Long marca_id;
    private String marca_nombre;

    private Long usuario_id;
    private String usuario_username;
}

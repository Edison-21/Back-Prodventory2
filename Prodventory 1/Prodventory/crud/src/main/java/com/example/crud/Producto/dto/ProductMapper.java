// src/main/java/com/example/crud/Producto/dto/ProductMapper.java
package com.example.crud.Producto.dto;

import com.example.crud.Producto.ProductEntity;

public class ProductMapper {

    public static ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId_producto(entity.getId_producto());
        dto.setNombre_producto(entity.getNombre_producto());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock());

        if (entity.getCategoria() != null) {
            dto.setCategoria_id(entity.getCategoria().getId_categoria());
            dto.setCategoria_nombre(entity.getCategoria().getNombre_categoria());
        }

        if (entity.getMarca() != null) {
            dto.setMarca_id(entity.getMarca().getId());
            dto.setMarca_nombre(entity.getMarca().getNombre());
        }

        if (entity.getUsuario() != null) {
            dto.setUsuario_id(entity.getUsuario().getId());
            dto.setUsuario_username(entity.getUsuario().getUsername());
        }

        return dto;
    }
}

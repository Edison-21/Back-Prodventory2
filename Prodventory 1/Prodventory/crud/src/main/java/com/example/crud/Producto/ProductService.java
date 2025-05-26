package com.example.crud.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    // Guardar un nuevo producto
    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }

    // Actualizar un producto por ID
    public ProductEntity update(Long id, ProductEntity entity) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductEntity productToUpdate = existingProduct.get();
            productToUpdate.setNombre_producto(entity.getNombre_producto());
            productToUpdate.setPrecio(entity.getPrecio());
            productToUpdate.setStock(entity.getStock());
            return productRepository.save(productToUpdate);
        }
        return null; // O lanzar una excepción personalizada si se requiere
    }

    // Obtener un producto por ID
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    // Eliminar un producto por ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

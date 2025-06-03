package com.example.crud.Marca;

import com.example.crud.Usuario.UsuarioEntity;
import com.example.crud.Producto.ProductEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(max = 30, message = "El nombre no debe superar los 30 caracteres")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference("usuario-marca")
    private UsuarioEntity usuario;



    @OneToMany(mappedBy = "marca")
    @JsonManagedReference("marca-producto")
    private List<ProductEntity> productos;
}

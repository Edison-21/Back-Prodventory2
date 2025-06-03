package com.example.crud.Usuario;

import java.util.List;

import com.example.crud.Categoria.CategoriaEntity;
import com.example.crud.Marca.MarcaEntity;
import com.example.crud.Producto.ProductEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email es inválido")
    private String email;

    @NotBlank(message = "El número de celular es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El celular debe tener exactamente 10 dígitos")
    private String celular;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-producto")
    private List<ProductEntity> productos;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-categoria")
    private List<CategoriaEntity> categorias;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-marca")
    private List<MarcaEntity> marcas;

}

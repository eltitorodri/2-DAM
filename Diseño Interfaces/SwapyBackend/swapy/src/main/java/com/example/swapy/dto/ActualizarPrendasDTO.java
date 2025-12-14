package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size; // Añadido para validar longitud si la DB es pequeña
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import jakarta.annotation.Nullable; // Opcional, dependiendo de la versión/librería

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarPrendasDTO {

    // Los campos de texto editables SÍ deberían mantener @NotBlank si el usuario los envía
    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(max = 150, message = "El titulo es demasiado largo") // Añade esta validación para evitar Data Truncation
    private String titulo;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(max = 1000, message = "La descripcion es demasiado larga")
    private String descripcion;

    // Estado y TipoGuardado requieren validación si se envían
    @NotBlank(message = "Debes de indicar el estado (Prestamo/Intercambio)")
    @Size(max = 12, message = "Estado excede el limite de la DB") // Máximo 12 (Intercambio)
    private String estado;

    @NotBlank(message = "Debes de indicar el tipo de guardado")
    @Size(max = 9, message = "Tipo de Guardado excede el limite de la DB") // Máximo 9 (Pendiente/Guardado)
    private String tipoGuardado;

    // Las relaciones generalmente se actualizan, pero ya no son NECESARIAMENTE @NotNull si permites una prenda sin ellas.
    // Si la DB REQUIERE estos IDs, mantén @NotNull.
    @NotNull(message = "Debes de indicar una categoría")
    private Integer categorias;

    @NotNull(message = "Debes de indicar una marca")
    private Integer marcas;

    @NotNull(message = "Debes de indicar un tipo de prenda")
    private Integer prendasTipo;

    // Usuario e Imagen: Normalmente NO se actualizan. Si el Front-end los envía, relaja la restricción.
    // Usar @Nullable o simplemente quitar @NotNull si permites que el Front-end los omita.
    // Si los mantienes:
    @Nullable // Usar @Nullable o dejar sin anotación
    private Integer usuario;

    @Nullable // Usar @Nullable o dejar sin anotación
    private Integer imagen;

    // Colores sigue siendo la lista de IDs
    @NotNull(message = "Debes de indicar algun color")
    private List<Integer> colores;
}
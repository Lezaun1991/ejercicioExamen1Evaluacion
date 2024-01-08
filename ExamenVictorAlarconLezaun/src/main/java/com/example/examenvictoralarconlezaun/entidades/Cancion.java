package com.example.examenvictoralarconlezaun.entidades;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
    private String id;
    @NotEmpty
    private String nombreCantante;
    @NotEmpty
    private String nombreCancion;
    private boolean actual;
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaPublicacion;
    private EstiloCanciones estiloCanciones;
    private String datosAdicionales;
}

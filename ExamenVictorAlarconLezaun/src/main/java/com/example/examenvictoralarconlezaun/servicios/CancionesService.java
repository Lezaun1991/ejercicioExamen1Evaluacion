package com.example.examenvictoralarconlezaun.servicios;

import com.example.examenvictoralarconlezaun.entidades.Cancion;
import com.example.examenvictoralarconlezaun.entidades.EstiloCanciones;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CancionesService {
    private ArrayList<Cancion> listaCanciones = new ArrayList<>();

    public List<Cancion> findAll() {
        return listaCanciones;
    }
    public Cancion findById(String id) {
        Cancion cancionBuscado = listaCanciones.stream()
                .filter(cliente ->id.equals(cliente.getId()))
                .findAny()
                .orElse(null);
        return cancionBuscado;
    }
    public Cancion add(Cancion cancion){
        listaCanciones.add(cancion);
        return cancion;
    }

    @PostConstruct
    public void initMascota() {
        listaCanciones.addAll(
                Arrays.asList(
                        Cancion.builder()
                                .id("1")
                                .actual(true)
                                .estiloCanciones(EstiloCanciones.REGGAETON)
                                .nombreCancion("Mirala Bien")
                                .nombreCantante("Nicky Jam")
                                .fechaPublicacion(LocalDate.of(2015,5,19))
                                .datosAdicionales("Una de las primeras canciones de reggaeton que yo escuche en mi vida")
                                .build(),
                        Cancion.builder()
                                .id("2")
                                .actual(false)
                                .estiloCanciones(EstiloCanciones.CLASICA)
                                .nombreCancion("La Primavera")
                                .nombreCantante("Vivaldi")
                                .fechaPublicacion(LocalDate.of(1845,4,13))
                                .datosAdicionales("A mi profe de musica le encantaba")
                                .build(),
                        Cancion.builder()
                                .id("3")
                                .actual(false)
                                .estiloCanciones(EstiloCanciones.FLAMENCO)
                                .nombreCancion("Estrella Blanca")
                                .nombreCantante("Fondo Flamenco")
                                .fechaPublicacion(LocalDate.of(2010,2,19))
                                .datosAdicionales("De pequeño la cantaba sin parar")
                                .build(),
                        Cancion.builder()
                                .id("4")
                                .actual(false)
                                .estiloCanciones(EstiloCanciones.POP_URBAN)
                                .nombreCancion("Corazon Partido")
                                .nombreCantante("Alejandro Sanz")
                                .fechaPublicacion(LocalDate.of(1996,10,3))
                                .datosAdicionales("A mi madre le encantaba, la habre escuchado cantando " +
                                        "esta cancion mil veces")
                                .build()
                ));

    }
}

package com.example.examenvictoralarconlezaun.Controladores;

import com.example.examenvictoralarconlezaun.entidades.Cancion;
import com.example.examenvictoralarconlezaun.servicios.CancionesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CancionesControler {

    private final CancionesService servicio;

    @GetMapping("/song/list")
    public String listado(Model model){
        model.addAttribute("listaCanciones",servicio.findAll());

        return "listadoCanciones";
    }
    @GetMapping("informacion/cancion/id")
    public String masInformacion(@PathVariable("id") String id){
        Cancion cancion = servicio.findById(id);
        return "";
    }
    @GetMapping("/song/new")
    public String crearCliente(Model model) {
        model.addAttribute("cancion", new Cancion());
        return "NuevaCancion";
    }
    @PostMapping("/new/song/submit")
    public String crearClienteSubmit(@Valid @ModelAttribute("cancion") Cancion cancion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "NuevaCancion";
        } else {
            servicio.add(cancion);
            return "redirect:/song/list";
        }
    }
}

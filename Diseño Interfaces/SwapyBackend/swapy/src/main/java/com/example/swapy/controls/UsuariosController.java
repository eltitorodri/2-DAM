package com.example.swapy.controls;

import com.example.swapy.Services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private UsuariosServicios usuariosServicios;

    @PostMapping("/crearUser")
    public void crearUser(@RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

}

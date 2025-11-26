package com.example.swapy.controls;

import com.example.swapy.services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.services.UsuariosServicios;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private com.example.swapy.services.UsuariosServicios usuariosServicios;

    @PostMapping("/crearUser")
    public void crearUser(@RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

}

package com.example.swapy.controls;

import com.example.swapy.services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private com.example.swapy.services.UsuariosServicios usuariosServicios;


    @PostMapping("/crearUser")
    public void crearUser(@RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

    @GetMapping("/infousuario/{id}")
    public UsuarioDTO consultarPerfilUsuario(@PathVariable Integer id){
        return usuariosServicios.consultarPerfilUsuario(id);
    }

}

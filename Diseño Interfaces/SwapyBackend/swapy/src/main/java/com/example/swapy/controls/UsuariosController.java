package com.example.swapy.controls;

import com.example.swapy.Services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private UsuariosServicios usuariosServicios;

    @PostMapping("/crearUser")
    public void crearUser(@RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

    @GetMapping("/consultarperfil")
    public UsuarioDTO consultarPerfil(@RequestParam("nickname") String nickname){
        return service.consultaPerfil();
    }

}

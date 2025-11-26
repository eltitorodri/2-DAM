package com.example.swapy.controls;

import com.example.swapy.services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.services.UsuariosServicios;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private com.example.swapy.services.UsuariosServicios usuariosServicios;

    @PostMapping("/crearUser")
    public void crearUser(@RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

<<<<<<< HEAD
    @GetMapping("/consultarperfil")
    public UsuarioDTO consultarPerfil(@RequestParam("nickname") String nickname){
        return service.consultaPerfil();
    }

=======
>>>>>>> 20013d5452ab45c03f1728a4cf5531f7bb8fb6c3
}

package med.voll.api2.controller;

import jakarta.validation.Valid;
import med.voll.api2.domain.usuarios.DatosAutenticacionUsuario;
import med.voll.api2.domain.usuarios.Usuario;
import med.voll.api2.infra.security.DatosJWTToken;
import med.voll.api2.infra.security.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServices tokenServices;
    @PostMapping
    public ResponseEntity autenticarUsuario( @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication Authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(Authtoken);
        var JWTtoken = tokenServices.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}

package med.voll.api2.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api2.domain.usuarios.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.RemoteException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServices tokenServices;
    @Autowired
    private usuarioRepository repositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ","");

        if(authHeader != null){
            var token = authHeader.replace("Bearer ","");
            var subject = tokenServices.getSubject(token);
            if(subject != null){
                //token valido
                var usuario = repositorio.findByLogin(subject);
                //forzar inicio de sesion
                var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }
        filterChain.doFilter(request,response);

    }
}

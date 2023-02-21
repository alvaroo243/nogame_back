package grupo2.nogame_rest.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
    //Se ejecutará en cada petición de la API Rest (por heredar de OncePerRequestFilter) y comprobará que sea válido el token utilizando el provider
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try { //Comprueba el token y si es valido permite el acceso al recurso.
            String token = getToken(req);
            if (token != null && jwtProvider.validateToken(token)) { //Token valido
                String emailUsuario = jwtProvider.getEmailUsuarioFromToken(token); //Extraer email del token
                UserDetails userDetails = userDetailsService.loadUserByUsername(emailUsuario); //Buscamos UsuarioPrincipal(UserDetails) logeado
                //Obtenemos el UserNamePasswordAuthenticationToken en base al userDetails y sus autorizaciones
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth); //aplicamos autorización al contexto
                
            }
        } catch (Exception e) { // Si falla al autentificación 
            logger.error("Fallo de autentificación del token JWT: " + e.getMessage());
        }
        // No falla autentificación y permitimos la petición 
        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
    
}

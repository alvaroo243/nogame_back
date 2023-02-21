package grupo2.nogame_rest.security.jwt;

import io.jsonwebtoken.*;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import grupo2.nogame_rest.security.entity.UsuarioPrincipal;



@Component
public class JwtProvider { // Se encargará de generar el token y comprobar su validez
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}") //valor en application.properties
    private String secret;
    @Value("${jwt.expiration}") //valor en application.properties
    private int expiration;

    public String generateToken(Authentication authentication) { //genera el token
        //Obtenemos el usuario principal (UserDetails)
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        //Configuramos nickname (getUsername), fecha de expedición, fecha de expiración y firmamos
        return Jwts.builder().setSubject(usuarioPrincipal.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
    
    public String getEmailUsuarioFromToken(String token) { //extrae el nickname del token
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) { //valida el token
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio");
        } catch (SignatureException e) {
            logger.error("Fallo en la firma");
        }
        return false;
    }
}

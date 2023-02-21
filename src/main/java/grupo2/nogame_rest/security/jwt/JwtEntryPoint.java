package grupo2.nogame_rest.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    //Comprueba si hay un toquen válido y si no lanza error de autenticación 401

    //SOLO EN DESARROLLO: utilizamos un logger para ver que tipo de error nos da
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("Fallo en el método commence");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
}
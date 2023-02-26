package grupo2.nogame_rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import grupo2.nogame_rest.security.jwt.JwtEntryPoint;
import grupo2.nogame_rest.security.jwt.JwtTokenFilter;
import grupo2.nogame_rest.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
                        //'prePostEnabled' permite configurar que métodos solo tiene acceso el administrador
public class MainSecurity {
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl; //Convierte la clase UsuarioDb en UsuarioPrincipal (UserDetails)

    @Autowired
    JwtEntryPoint jwtEntryPoint; //Si no hay token o no es valido devuelve error 401 "No Autorizado"

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //permite cifrar la contraseña
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Inhabilitamos csrf con csrf().disable(). Si necesitamos cookies no sería buena idea
        //Todo lo que sea '/auth/' estará autorizado y para el resto hará falta el token JWT de autentificación 
        //Para controlar las excepciones utilizamos exceptionHandling indicando que utilice jwtEntryPoint
        //Sesión sin estados (sin cookies) mediante STATELESS
        //Añadimos el jwtTokenFilter para que se compruebe el token en cada petición y va a pasar el usuario al 
        //contexto de autenticación
        http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/**",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/configuration/**",
        "/v3/api-docs/**"
        ).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}

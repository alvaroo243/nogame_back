package grupo2.nogame_rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.security.entity.UsuarioPrincipal;
import grupo2.nogame_rest.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Método que debemos sobreescribir (debe tener este nombre) de la interfaz UserDetaisService.
        //En nuestro caso buscamos por nickname en la BD y devolvemos un UsuarioPrincipal,
        //que es una implementacion de la interfaz UserDetails.
        UserDb usuario = userService.getByEmail(email).get();
        return UsuarioPrincipal.build(usuario);
    }
}

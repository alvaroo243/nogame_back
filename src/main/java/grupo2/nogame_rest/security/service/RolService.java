package grupo2.nogame_rest.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo2.nogame_rest.security.entity.RolDb;
import grupo2.nogame_rest.security.enums.RolNombre;
import grupo2.nogame_rest.security.repository.RolRepository;

@Service
@Transactional // Mantiene la coherencia de la BD si hay varios accesos
public class RolService {
    
    @Autowired
    RolRepository rolRepository;

    public Optional<RolDb> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(RolDb rol) {
        rolRepository.save(rol);
    }
}

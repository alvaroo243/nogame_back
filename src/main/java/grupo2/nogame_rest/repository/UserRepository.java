package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.db.UserId;

public interface UserRepository extends JpaRepository<UserDb ,UserId>{
    public Optional<UserDb> findById(Long id);
    public Optional<UserDb> findByEmail(String email);
    public void deleteById(Long id);
    public void deleteByEmail(String email);
}
package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import grupo2.nogame_rest.model.db.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Long>{
    public Optional<UserDb> findByEmail(String email);
}
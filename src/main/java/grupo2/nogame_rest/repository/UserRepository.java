package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.db.UserEditDb;

public interface UserRepository extends JpaRepository<UserDb ,Long>{
    public Optional<UserDb> findByEmail(String email);
    public void deleteByEmail(String email);
    Optional<UserDb> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    UserEditDb save(UserEditDb userEditDb);
}
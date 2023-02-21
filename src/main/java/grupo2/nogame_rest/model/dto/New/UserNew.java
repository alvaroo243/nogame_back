package grupo2.nogame_rest.model.dto.New;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserNew implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime created_ts;
    //Al utilizar API Rest utilizamos objetos tipo Json y 
    //es mejor que sean cadenas para agilizar el tráfico
    private Set<String> roles = new HashSet<>();
}

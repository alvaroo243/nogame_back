package grupo2.nogame_rest.model.dto.New;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserNew implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime created_ts;
}

package grupo2.nogame_rest.model.dto.List;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserList implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nickname;
    private String email;
    private LocalDateTime created_ts;
}

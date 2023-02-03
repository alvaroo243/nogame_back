package grupo2.nogame_rest.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEdit implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nickname;
    private String email;
    private String password;
}

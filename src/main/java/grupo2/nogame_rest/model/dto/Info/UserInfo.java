package grupo2.nogame_rest.model.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    private String email;
    private String password;
}

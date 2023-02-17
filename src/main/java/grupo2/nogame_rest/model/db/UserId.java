package grupo2.nogame_rest.model.db;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserId implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
}

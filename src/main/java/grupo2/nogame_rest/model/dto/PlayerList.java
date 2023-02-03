package grupo2.nogame_rest.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerList implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nicknameUser;
    private String nameType;
    private Integer level;
}

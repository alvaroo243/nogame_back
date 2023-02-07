package grupo2.nogame_rest.model.dto.Edit;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerEdit implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long typeId;
    private Long userId;
    private Integer level;
}
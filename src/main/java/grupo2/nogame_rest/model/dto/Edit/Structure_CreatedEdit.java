package grupo2.nogame_rest.model.dto.Edit;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Structure_CreatedEdit implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long planet_id;
    private Long structure_id;
    private Long player_id;
    private Boolean upgrade_ongoing;
    private Integer current_level;
}

package grupo2.nogame_rest.model.dto.Edit;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanetEdit implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Long playerId;
    private String image;

    public PlanetEdit(String name, Long playerId, String image) {
        this.name = name;
        this.playerId = playerId;
        this.image = image;
    }
}
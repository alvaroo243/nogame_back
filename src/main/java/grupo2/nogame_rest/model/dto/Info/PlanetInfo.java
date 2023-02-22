package grupo2.nogame_rest.model.dto.Info;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanetInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String image;
}

package grupo2.nogame_rest.model.dto.List;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import grupo2.nogame_rest.model.dto.Info.PlanetInfoNombre;
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
    private Set<PlanetInfoNombre> planetsInfoNombres = new HashSet<>();
    private Integer level;
}

package grupo2.nogame_rest.model.dto.List;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import grupo2.nogame_rest.model.dto.Info.PlanetInfo;
import grupo2.nogame_rest.model.dto.Info.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerList implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private UserInfo userInfo;
    private String nameType;
    private Set<PlanetInfo> planetsInfo = new HashSet<>();
    private Integer level;
}

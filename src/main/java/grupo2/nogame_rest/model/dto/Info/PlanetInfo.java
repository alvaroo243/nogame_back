package grupo2.nogame_rest.model.dto.Info;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    private Boolean first;
    private Set<Resource_StorageInfo> storagesInfo = new HashSet<>();
}
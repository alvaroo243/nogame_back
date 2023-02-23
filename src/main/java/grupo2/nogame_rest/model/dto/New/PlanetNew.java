package grupo2.nogame_rest.model.dto.New;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanetNew implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Long playerId;
    private String image;
    private Boolean first;
    private LocalDate created_ts;

    public PlanetNew(String name, Long playerId, String image, Boolean first, LocalDate created_ts) {
        this.name = name;
        this.playerId = playerId;
        this.image = image;
        this.first = first;
        this.created_ts = created_ts;
    }
}
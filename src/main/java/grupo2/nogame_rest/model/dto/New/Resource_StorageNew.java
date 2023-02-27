package grupo2.nogame_rest.model.dto.New;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resource_StorageNew implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long resourceId;
    private Long structureId;
    private Long planetId;
    private Integer quantity;


    public Resource_StorageNew(Long resourceId, Long structureId, Long planetId, Integer quantity) {
        this.resourceId = resourceId;
        this.structureId = structureId;
        this.planetId = planetId;
        this.quantity = quantity;
    }
}

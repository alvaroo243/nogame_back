package grupo2.nogame_rest.model.dto.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resource_storageList {
    private Long id;
    private String resource_name;
    private String planet_name;
    private Integer quantity;
}

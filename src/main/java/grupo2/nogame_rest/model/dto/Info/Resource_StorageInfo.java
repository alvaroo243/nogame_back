package grupo2.nogame_rest.model.dto.Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resource_StorageInfo {
    private Long id;
    private String resource_name;
    private String planet_name;
    private Integer quantity;
}

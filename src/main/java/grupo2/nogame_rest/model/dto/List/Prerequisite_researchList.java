package grupo2.nogame_rest.model.dto.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prerequisite_researchList {
    private Long id;
    private String structure_name;
    private String resource_name;
    private Integer level_required;  
}

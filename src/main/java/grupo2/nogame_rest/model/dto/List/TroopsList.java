package grupo2.nogame_rest.model.dto.List;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TroopsList implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Boolean isSpecial;
    private Integer value;
    private Long type;
}

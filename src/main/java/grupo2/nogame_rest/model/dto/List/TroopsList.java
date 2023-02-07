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
    private Integer special;
    private Integer value;
    private Long type;

    public boolean isSpecial(){
        if (this.special == 0) {
            return false;
        } else {
            return true;
        }
    }
}

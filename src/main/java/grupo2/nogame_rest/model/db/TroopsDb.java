package grupo2.nogame_rest.model.db;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "troops")
public class TroopsDb implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer special;
    private Integer value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private TypeDb type;

    public boolean isSpecial(){
        if (this.special == 0) {
            return false;
        } else {
            return true;
        }
    }
}
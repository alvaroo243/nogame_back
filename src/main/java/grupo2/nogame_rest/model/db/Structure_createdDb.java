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
@Table(name = "structure_created")
public class Structure_createdDb implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planet")
    private PlanetDb planet_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "structure")
    private StructureDb structure_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player")
    private PlayerDb player_id;
    private Boolean upgrade_ongoing;
    private Integer current_level;
    
    
}
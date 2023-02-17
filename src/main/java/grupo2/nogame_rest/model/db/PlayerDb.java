package grupo2.nogame_rest.model.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(exclude = { "planetsDb"})
@ToString(exclude = { "planetsDb"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "player")
public class PlayerDb implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private TypeDb typeDb;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JoinColumn(name = "userEmail", referencedColumnName = "email")
    private UserDb userDb;
    @OneToMany(mappedBy = "playerDb")
    private Set<PlanetDb> planetsDb = new HashSet<>();
    private Integer level;
}
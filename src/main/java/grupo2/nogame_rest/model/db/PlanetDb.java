package grupo2.nogame_rest.model.db;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(exclude = {"storagesDb"})
@ToString(exclude = {"storagesDb"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "planet")
public class PlanetDb implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player")
    private PlayerDb playerDb;
    private String image;
    private Integer first;
    @Column(name = "created_ts", columnDefinition = "TIMESTAMP")
    private LocalDate created_ts;
    @OneToMany(mappedBy = "planetDb")
    private Set<Resource_storageDb> storagesDb = new HashSet<>();

    public boolean isFirst(){
        if (this.first == 0) {
            return false;
        } else {
            return true;
        }
    }
}

package grupo2.nogame_rest.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import grupo2.nogame_rest.model.dto.List.PlayerList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private PlayerList playerList;
    private Collection<? extends GrantedAuthority> authorities;
    //Constructor sin el atributo 'bearer'
    public JwtDto(String token, PlayerList playerList, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.playerList = playerList;
        this.authorities = authorities;
    }
    
}

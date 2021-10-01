package info.wargame.backendinfowargamev3.entity.user;

import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email;

    private String password;

    private String nickName;

    private UserAuthority userAuthority;

    private Integer score;

    public User scoreUp(Integer score) {
        this.score += score;

        return this;
    }
}

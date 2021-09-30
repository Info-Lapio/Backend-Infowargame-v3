package info.wargame.backendinfowargamev3.entity.ban_user;

import info.wargame.backendinfowargamev3.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BanUser {
    @Id
    private String email;

    @MapsId
    @OneToOne
    @JoinColumn(name = "email")
    private User user;
    
    private LocalDateTime banDate;
}

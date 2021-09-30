package info.wargame.backendinfowargamev3.entity.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noticeId;

    private String title;

    @Column(length = 1000)
    private String content;

    private LocalDateTime noticeAt;

    private String writer;
}

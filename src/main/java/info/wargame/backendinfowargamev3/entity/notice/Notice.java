package info.wargame.backendinfowargamev3.entity.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    public Notice updateNotice(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.noticeAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        return this;
    }
}

package info.wargame.backendinfowargamev3.entity.event;

import info.wargame.backendinfowargamev3.entity.event_image.EventImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String eventName;

    private String content;

    @OneToMany(mappedBy = "event_image", cascade = CascadeType.ALL)
    private List<EventImage> eventImages;


    public Event updateEvent(String eventName, String content) {
        this.content = content;
        this.eventName = eventName;

        return this;
    }
}

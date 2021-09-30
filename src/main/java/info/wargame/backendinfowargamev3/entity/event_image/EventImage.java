package info.wargame.backendinfowargamev3.entity.event_image;

import info.wargame.backendinfowargamev3.entity.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EventImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventImageId;

    private String imageName;

    private Long eventId;
}

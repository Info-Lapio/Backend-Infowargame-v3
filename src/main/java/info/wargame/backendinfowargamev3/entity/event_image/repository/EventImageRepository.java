package info.wargame.backendinfowargamev3.entity.event_image.repository;

import info.wargame.backendinfowargamev3.entity.event_image.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventImageRepository extends JpaRepository<EventImage, Long> {
    @Query("select e.imageName from EventImage e where e.event.eventId = ?1")
    List<String> getAllNames(Long eventId);
    Optional<EventImage> findByImageName(String imageName);
    void deleteAllByEvent_EventId(Long eventId);
}

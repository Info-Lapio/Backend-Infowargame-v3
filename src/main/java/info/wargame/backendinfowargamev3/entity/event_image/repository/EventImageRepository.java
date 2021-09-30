package info.wargame.backendinfowargamev3.entity.event_image.repository;

import info.wargame.backendinfowargamev3.entity.event_image.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImageRepository extends JpaRepository<EventImage, Long> {
}

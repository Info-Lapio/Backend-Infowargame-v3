package info.wargame.backendinfowargamev3.entity.event.repository;

import info.wargame.backendinfowargamev3.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

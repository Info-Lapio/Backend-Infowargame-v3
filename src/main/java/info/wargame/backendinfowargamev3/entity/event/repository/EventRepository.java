package info.wargame.backendinfowargamev3.entity.event.repository;

import info.wargame.backendinfowargamev3.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAll(Pageable pageable);
    Optional<Event> findByEventId(Long eventId);
    void deleteByEventId(Long eventId);
}

package info.wargame.backendinfowargamev3.service.event;

import info.wargame.backendinfowargamev3.payload.request.UpdateEventRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteEventRequest;
import info.wargame.backendinfowargamev3.payload.response.EventResponse;

import java.util.List;

public interface EventService {
    List<EventResponse> mainPageEvents();
    List<EventResponse> getEvents(int pageNum);
    void writeEvent(WriteEventRequest writeEventRequest);
    void updateEvent(Long eventId, UpdateEventRequest updateEventRequest);
    void deleteEvent(Long eventId);
}

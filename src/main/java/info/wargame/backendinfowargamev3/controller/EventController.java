package info.wargame.backendinfowargamev3.controller;

import info.wargame.backendinfowargamev3.payload.request.UpdateEventRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteEventRequest;
import info.wargame.backendinfowargamev3.payload.response.EventResponse;
import info.wargame.backendinfowargamev3.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/event/v1")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventResponse> mainPageEvents() {
        return eventService.mainPageEvents();
    }

    @GetMapping("/{pageNum}")
    public List<EventResponse> readEvents(@PathVariable int pageNum) {
        return eventService.getEvents(pageNum);
    }

    @PostMapping
    public void writeEvent(@RequestParam String title,
                           @RequestParam String content,
                           @RequestParam List<MultipartFile> images) {
        eventService.writeEvent(
                WriteEventRequest.builder()
                        .content(content)
                        .title(title)
                        .images(images)
                        .build()
        );
    }

    @PutMapping("/{eventId}")
    public void updateEvent(@RequestParam String title,
                            @RequestParam String content,
                            @RequestParam List<MultipartFile> images,
                            @PathVariable Long eventId) {
        eventService.updateEvent(
                eventId,
                UpdateEventRequest.builder()
                        .title(title)
                        .content(content)
                        .images(images)
                        .build()
        );
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {

    }
}

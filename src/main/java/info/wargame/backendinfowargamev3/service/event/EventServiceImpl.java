package info.wargame.backendinfowargamev3.service.event;

import info.wargame.backendinfowargamev3.entity.event.Event;
import info.wargame.backendinfowargamev3.entity.event.repository.EventRepository;
import info.wargame.backendinfowargamev3.entity.event_image.EventImage;
import info.wargame.backendinfowargamev3.entity.event_image.repository.EventImageRepository;
import info.wargame.backendinfowargamev3.entity.user.User;
import info.wargame.backendinfowargamev3.entity.user.enums.UserAuthority;
import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.payload.request.UpdateEventRequest;
import info.wargame.backendinfowargamev3.payload.request.WriteEventRequest;
import info.wargame.backendinfowargamev3.payload.response.EventResponse;
import info.wargame.backendinfowargamev3.security.auth.AuthenticationFacade;
import info.wargame.backendinfowargamev3.utils.FileValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventImageRepository eventImageRepository;

    private final AuthenticationFacade authenticationFacade;
    private final FileValidate fileValidate;

    private final int PAGE_NUM = 20;
    private final int MAIN_NUM = 5;

    @Value("${image.dir}")
    private String imageDir;

    private List<EventResponse> returnResponse(int pageNum, int PAGE) {
        Page<Event> events = eventRepository.findAll(PageRequest.of(pageNum, PAGE));

        List<EventResponse> responses = new ArrayList<>();

        for(Event event : events) {
            responses.add(
                    EventResponse.builder()
                            .eventId(event.getEventId())
                            .eventName(event.getEventName())
                            .content(event.getContent())
                            .images(eventImageRepository.getAllNames(event.getEventId()))
                            .build()
            );
        }

        return responses;
    }

    @Override
    public List<EventResponse> mainPageEvents() {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        return returnResponse(0, MAIN_NUM);
    }

    @Override
    public List<EventResponse> getEvents(int pageNum) {
        userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        return returnResponse(pageNum, PAGE_NUM);
    }

    @Override
    public void writeEvent(WriteEventRequest writeEventRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(!user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Event event = eventRepository.save(
                Event.builder()
                        .eventName(writeEventRequest.getTitle())
                        .content(writeEventRequest.getContent())
                        .build()
        );

        List<EventImage> eventImages = new ArrayList<>();

        for(MultipartFile image : writeEventRequest.getImages()) {
            eventImages.add(
                    EventImage.builder()
                            .imageName(fileValidate.validateImageAndSave(image))
                            .eventId(event.getEventId())
                            .build()
            );
        }

        eventImageRepository.saveAll(eventImages);
    }

    @Override
    public void updateEvent(Long eventId, UpdateEventRequest updateEventRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(!user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Event event = eventRepository.findByEventId(eventId)
                .orElseThrow(RuntimeException::new);

        eventRepository.save(
                event.updateEvent(updateEventRequest.getTitle(), updateEventRequest.getContent())
        );

        for(EventImage eventImage : event.getEventImages()) {
            File file = new File(imageDir, eventImage.getImageName());
            if(!file.exists())
                throw new RuntimeException("event image not found");

            file.delete();
        }

        eventImageRepository.deleteAllByEventId(eventId);

        List<EventImage> images = new ArrayList<>();
        for(MultipartFile image : updateEventRequest.getImages()) {
            images.add(
                    EventImage.builder()
                            .eventId(eventId)
                            .imageName(fileValidate.validateImageAndSave(image))
                            .build()
            );
        }

        eventImageRepository.saveAll(images);
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        User user = userRepository.findByEmail(authenticationFacade.getEmail())
                .orElseThrow(RuntimeException::new);

        if(user.getUserAuthority().equals(UserAuthority.ADMIN))
            throw new RuntimeException("user not admin");

        Event event = eventRepository.findByEventId(eventId)
                .orElseThrow(RuntimeException::new);

        for(EventImage eventImage : event.getEventImages()) {
            File file = new File(imageDir, eventImage.getImageName());
            if(!file.exists())
                throw new RuntimeException("event iamge not found");

            file.delete();
        }

        eventRepository.deleteByEventId(eventId);
    }
}

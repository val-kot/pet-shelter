package course.controller;

import course.entity.Event;
import course.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            return ResponseEntity.ok(optionalEvent.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/family/{familyId}")
    public ResponseEntity<List<Event>> getEventsByFamilyId(@PathVariable Long familyId) {
        List<Event> events = eventRepository.findByFamilyId(familyId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<Event>> getEventsByPetId(@PathVariable Long petId) {
        List<Event> events = eventRepository.findByPetId(petId);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // (проверка) есть ли у питомца уже существующие записи в таблице Event
        if (eventRepository.hasPetInEvents(event.getPet().getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> optionalExistingEvent = eventRepository.findById(id);
        if (optionalExistingEvent.isPresent()) {
            Event existingEvent = optionalExistingEvent.get();

            // Проверяем, не используется ли питомец уже в другом событии
            if (eventRepository.hasPetInEvents(event.getPet().getId()) && !existingEvent.getPet().getId().equals(event.getPet().getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            existingEvent.setDate(event.getDate());
            existingEvent.setFamily(event.getFamily());
            existingEvent.setPet(event.getPet());
            Event updatedEvent = eventRepository.save(existingEvent);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}





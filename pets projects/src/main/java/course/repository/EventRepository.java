package course.repository;

import course.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Методы для работы с объектами Event:

    // Получение всех событий
    // List<Event> findAll();

    // Получение события по id
    // Optional<Event> findById(Long id);

    // Создание нового события
    // Event save(Event event);

    // Обновление существующего события
    // Event save(Event event);

    // Удаление события по id
    // void deleteById(Long id);

    // Получение событий по id семьи
    // List<Event> findByFamilyId(Long familyId);

    // Получение событий по id питомца
    // List<Event> findByPetId(Long petId);

    // Проверка, есть ли у питомца уже существующие записи в таблице Event
    @Query("SELECT COUNT(e) > 0 FROM Event e WHERE e.pet.id = :petId")
    boolean hasPetInEvents(Long petId);

    List<Event> findByPetId(Long petId);

    List<Event> findByFamilyId(Long familyId);
}


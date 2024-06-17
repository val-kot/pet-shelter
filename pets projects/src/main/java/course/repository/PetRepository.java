package course.repository;

import course.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Методы для работы с объектами Pet:

    // Получение всех питомцев
    // List<Pet> findAll();

    // Получение питомца по id
    // Optional<Pet> findById(Long id);

    // Создание нового питомца
    // Pet save(Pet pet);

    // Обновление существующего питомца
    // Pet save(Pet pet);

    // Удаление питомца по id
    // void deleteById(Long id);
}

package course.repository;

import course.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    // Методы для работы с объектами Family:

    // Получение всех семей
    // List<Family> findAll();

    // Получение семьи по id
    //  Optional<Family> findById(Long id);

    // Создание новой семьи
    // Family save(Family family);

    // Обновление существующей семьи
    // Family save(Family family);

    // Удаление семьи по id
    // void deleteById(Long id);
}


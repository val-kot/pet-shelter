package course.controller;

import course.entity.Family;
import course.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping
    public ResponseEntity<List<Family>> getAllFamilies() {
        List<Family> families = familyRepository.findAll();
        return ResponseEntity.ok(families);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable Long id) {
        Optional<Family> optionalFamily = familyRepository.findById(id);
        if (optionalFamily.isPresent()) {
            return ResponseEntity.ok(optionalFamily.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Family> createFamily(@RequestBody Family family) {
        Family savedFamily = familyRepository.save(family);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamily);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Family> updateFamily(@PathVariable Long id, @RequestBody Family family) {
        Optional<Family> optionalFamily = familyRepository.findById(id);
        if (optionalFamily.isPresent()) {
            Family existingFamily = optionalFamily.get();
            existingFamily.setFio(family.getFio());
            existingFamily.setAddress(family.getAddress());
            Family updatedFamily = familyRepository.save(existingFamily);
            return ResponseEntity.ok(updatedFamily);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamily(@PathVariable Long id) {
        Optional<Family> optionalFamily = familyRepository.findById(id);
        if (optionalFamily.isPresent()) {
            familyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

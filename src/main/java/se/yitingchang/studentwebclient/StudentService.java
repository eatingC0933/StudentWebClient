package se.yitingchang.studentwebclient;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public List<Student> getAll() {
        return repository.findAll();
    }


    public Student add(Student student) {
        return repository.save(student);
    }


    public Student update(int id, Student student) {
        Optional<Student> existing = repository.findById(id);
        if (existing.isPresent()) {
            student.setId(id);
            return repository.save(student);
        }
        throw new EntityNotFoundException("Student with id " + id + " not found.");
    }


    public void delete(int id) {
        repository.deleteById(id);
    }
}


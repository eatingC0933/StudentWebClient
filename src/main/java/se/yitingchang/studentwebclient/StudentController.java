package se.yitingchang.studentwebclient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.add(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        service.delete(id);
    }
}


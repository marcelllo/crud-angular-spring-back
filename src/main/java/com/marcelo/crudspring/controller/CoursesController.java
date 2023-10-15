package com.marcelo.crudspring.controller;

import java.util.List;

import com.marcelo.crudspring.model.Course;
import com.marcelo.crudspring.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

  private final CourseRepository courseRepository;

  @GetMapping
  public List<Course> list() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  // public ResponseEntity<Course> create(@RequestBody Course course) {
  public Course create(@RequestBody Course course) {
    return courseRepository.save(course);
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
    return courseRepository.findById(id)
        .map(courseFound -> {

          courseFound.setName(course.getName());
          courseFound.setCategory(course.getCategory());

          return ResponseEntity.ok().body(courseRepository.save(courseFound));
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return this.courseRepository.findById(id)
            .map(course -> {
              this.courseRepository.delete(course);
              return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().<Void>build());
  }
}

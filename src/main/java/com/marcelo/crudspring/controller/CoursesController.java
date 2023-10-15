package com.marcelo.crudspring.controller;

import java.util.List;

import com.marcelo.crudspring.model.Course;
import com.marcelo.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/courses")
public class CoursesController {

  private final CourseService courseService;

  public CoursesController(CourseService courseService) {
      this.courseService = courseService;
  }

  @GetMapping
  public List<Course> list() {
    return courseService.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable @Positive Long id) {
    return courseService.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody @Valid Course course) {
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody Course course) {
    return courseService.update(id, course)
        .map(updated -> ResponseEntity.ok().body(updated))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
    return this.courseService.delete(id) ?
            ResponseEntity.noContent().<Void>build() :
            ResponseEntity.notFound().<Void>build();
  }
}

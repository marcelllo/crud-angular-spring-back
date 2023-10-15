package com.marcelo.crudspring.controller;

import com.marcelo.crudspring.dto.CourseDTO;
import com.marcelo.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/courses")
public class CoursesController {

  private final CourseService courseService;

  public CoursesController(CourseService courseService) {
      this.courseService = courseService;
  }

  @GetMapping
  public List<CourseDTO> list() {
    return courseService.list();
  }

  @GetMapping("/{id}")
  public CourseDTO findById(@PathVariable @Positive Long id) {
    return courseService.findById(id);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseDTO create(@RequestBody @Valid CourseDTO course) {
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public CourseDTO update(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody CourseDTO course) {
    return courseService.update(id, course);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    this.courseService.delete(id);
  }
}

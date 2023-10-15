package com.marcelo.crudspring.service;

import com.marcelo.crudspring.model.Course;
import com.marcelo.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(@NotNull @Positive Long id) {
        return this.courseRepository.findById(id);
    }

    public Course create(@Valid Course course) {
        return this.courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
            .map(courseFound -> {
                courseFound.setName(course.getName());
                courseFound.setCategory(course.getCategory());
                return courseRepository.save(courseFound);
            });
    }

    public boolean delete(@NotNull @Positive Long id) {
        return this.courseRepository.findById(id)
            .map(course -> {
                this.courseRepository.delete(course);
                return true;
            })
            .orElse(false);
    }
}

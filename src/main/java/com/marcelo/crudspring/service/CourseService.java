package com.marcelo.crudspring.service;

import com.marcelo.crudspring.dto.CourseDTO;
import com.marcelo.crudspring.dto.CoursePageDTO;
import com.marcelo.crudspring.dto.mapper.CourseMapper;
import com.marcelo.crudspring.exception.RecordNotFoundException;
import com.marcelo.crudspring.model.Course;
import com.marcelo.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

//    public List<CourseDTO> list() {
//        return courseRepository.findAll()
//                .stream()
//                .map(courseMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    public CoursePageDTO list(
            @NotNull @PositiveOrZero int page,
            @NotNull @PositiveOrZero @Max(25) int pageSize
    ) {
        Page<Course> coursePage = courseRepository.findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> courses = coursePage.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, coursePage.getTotalElements(), coursePage.getTotalPages());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
            .map(courseFound -> {
                var course = courseMapper.toEntity(courseDTO);

                courseFound.setName(course.getName());
                courseFound.setCategory(course.getCategory());
                courseFound.setLessons(course.getLessons());

                return courseMapper.toDTO(courseRepository.save(courseFound));
            })
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}

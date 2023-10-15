package com.marcelo.crudspring.dto.mapper;

import com.marcelo.crudspring.dto.CourseDTO;
import com.marcelo.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(CourseDTO courseDto) {
        if (courseDto == null) {
            return null;
        }

        return new Course(courseDto.id(), courseDto.name(), courseDto.category(), "Ativo");
    }
}

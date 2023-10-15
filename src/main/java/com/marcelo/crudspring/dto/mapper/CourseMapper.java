package com.marcelo.crudspring.dto.mapper;

import com.marcelo.crudspring.dto.CourseDTO;
import com.marcelo.crudspring.enums.Category;
import com.marcelo.crudspring.enums.Status;
import com.marcelo.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDto) {
        if (courseDto == null) {
            return null;
        }

        return new Course(courseDto.id(), courseDto.name(), convertCategoryValue(courseDto.category()), Status.ACTIVE);
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-End" -> Category.FRONT_END;
            case "Back-End" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}

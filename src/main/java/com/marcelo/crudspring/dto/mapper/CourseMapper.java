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

        return new CourseDTO(course.getId(), course.getName(), "Front-End");
    }

    public Course toEntity(CourseDTO courseDto) {
        if (courseDto == null) {
            return null;
        }

        return new Course(courseDto.id(), courseDto.name(), Category.FRONT_END, Status.Ativo);
    }
}

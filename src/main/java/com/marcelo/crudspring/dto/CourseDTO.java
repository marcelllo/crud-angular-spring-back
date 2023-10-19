package com.marcelo.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcelo.crudspring.enums.Category;
import com.marcelo.crudspring.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
    @JsonProperty("_id") Long id,
    @NotNull @NotBlank @Length(min = 5, max = 100) String name,
    @NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
    @NotNull @NotEmpty @Valid List<LessonDTO> lessons
) {}

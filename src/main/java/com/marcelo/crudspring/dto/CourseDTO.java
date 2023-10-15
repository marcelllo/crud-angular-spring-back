package com.marcelo.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
    @JsonProperty("_id") Long id,
    @NotNull @NotBlank @Length(min = 5, max = 100) String name,
    @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category
) {}

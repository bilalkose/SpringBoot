package com.bilalkose.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class TeacherDto {
    private Long teacherId;

    @NotEmpty(message = "ad soyad alanı boş olamaz")
    @Size(min = 1,max = 255)
    private String teacherNameSurname;

    @NotEmpty(message = "email alanı boş olamaz")
    @Email(message = "uygun format değil")
    private String teacherEmail;

    @NotEmpty(message = "password alanı boş olamaz")
    @Size(min = 4,max = 24)
    private String teacherPassword;
}

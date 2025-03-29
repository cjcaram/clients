package com.cjcaram.clients.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    @NotBlank(message = "Name required")
    @Size(min = 2, max = 100, message = "name must be greater than 2 and less than 100 characters")
    private String name;

    @NotBlank(message = "email required")
    @Email(message = "must use a valid email")
    private String email;

    @Past(message = "birthdate must be a valid date")
    private LocalDate birthdate;
}

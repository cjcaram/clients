package com.cjcaram.clients.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name required")
    @Size(min = 2, max = 100, message = "name must be greater than 2 and less than 100 characters")
    private String name;

    @NotBlank(message = "email required")
    @Email(message = "must use a valid email")
    @Column(unique = true)
    private String email;

    @Past(message = "birthdate must be a valid date")
    private LocalDate birthdate;

    private boolean active;
}

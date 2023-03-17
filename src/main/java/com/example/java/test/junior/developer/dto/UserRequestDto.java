package com.example.java.test.junior.developer.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NonNull
    private final Long id;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @Email(message = "Email should be valid")   // Використовувати javax.validation для верифікації ?
    @NotNull
    private final String email;

    @NonNull
    private final String password;  // Чи можна зберігати тут тимчасово пароль, чи безпечно це?
}
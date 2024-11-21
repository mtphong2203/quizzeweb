package com.maiphong.quizzeweb.dtos.question;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateDTO {
    private UUID id;

    @NotNull(message = "Content should not be null")
    private String content;

    @NotNull(message = "Type should not be null")
    private String questionType;

    @NotNull(message = "Active should not be null")
    private Boolean isActive;
}

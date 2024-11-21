package com.maiphong.quizzeweb.dtos.quiz;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    private UUID id;

    @NotNull(message = "Title should not be null")
    private String title;

    @NotNull(message = "Description should not be null")
    private String description;

    @PositiveOrZero(message = "Should equal or greater than zero")
    private double duration;

    @NotNull(message = "Active should not be null")
    private Boolean isActive;

}

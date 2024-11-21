package com.maiphong.quizzeweb.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.quizzeweb.dtos.quiz.QuizCreateDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizEditDTO;

public interface QuizService {
    List<QuizDTO> getAll();

    QuizDTO getById(UUID id);

    boolean create(QuizCreateDTO quizCreateDTO);

    boolean update(QuizEditDTO quizEditDTO);

    boolean delete(UUID id);

}

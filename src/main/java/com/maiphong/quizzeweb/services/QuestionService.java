package com.maiphong.quizzeweb.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.quizzeweb.dtos.question.QuestionCreateDTO;
import com.maiphong.quizzeweb.dtos.question.QuestionDTO;
import com.maiphong.quizzeweb.dtos.question.QuestionUpdateDTO;

public interface QuestionService {
    List<QuestionDTO> getAll();

    QuestionDTO getById(UUID id);

    boolean create(QuestionCreateDTO questionCreateDTO);

    boolean update(QuestionUpdateDTO questionUpdateDTO);

    boolean delete(UUID id);

}

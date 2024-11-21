package com.maiphong.quizzeweb.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.quizzeweb.dtos.question.QuestionCreateDTO;
import com.maiphong.quizzeweb.dtos.question.QuestionDTO;
import com.maiphong.quizzeweb.dtos.question.QuestionUpdateDTO;
import com.maiphong.quizzeweb.entities.Question;
import com.maiphong.quizzeweb.exceptions.ResourceNotFoundException;
import com.maiphong.quizzeweb.repositories.QuestionRepository;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDTO> getAll() {
        List<Question> questions = questionRepository.findAll();

        List<QuestionDTO> questionDTOs = questions.stream().map(question -> {
            QuestionDTO questionDTO = new QuestionDTO();

            questionDTO.setId(question.getId());
            questionDTO.setContent(question.getContent());
            questionDTO.setQuestionType(question.getQuestionType());
            questionDTO.setIsActive(question.getIsActive());

            return questionDTO;
        }).toList();

        return questionDTOs;
    }

    @Override
    public QuestionDTO getById(UUID id) {
        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            throw new ResourceNotFoundException("Question is not found");
        }

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setContent(question.getContent());
        questionDTO.setQuestionType(question.getQuestionType());
        questionDTO.setIsActive(question.getIsActive());

        return questionDTO;

    }

    @Override
    public boolean create(QuestionCreateDTO questionCreateDTO) {
        if (questionCreateDTO.getContent().equals("") || questionCreateDTO.getQuestionType().equals("")) {
            throw new IllegalArgumentException("Question create should be full");
        }

        Question newQuestion = new Question();
        newQuestion.setContent(questionCreateDTO.getContent());
        newQuestion.setQuestionType(questionCreateDTO.getQuestionType());
        newQuestion.setIsActive(questionCreateDTO.getIsActive());

        newQuestion = questionRepository.save(newQuestion);

        return newQuestion != null;

    }

    @Override
    public boolean update(QuestionUpdateDTO questionUpdateDTO) {
        if (questionUpdateDTO.getContent().equals("") || questionUpdateDTO.getQuestionType().equals("")) {
            throw new IllegalArgumentException("Question update should be full");
        }

        Question updateQuestion = questionRepository.findById(questionUpdateDTO.getId()).orElse(null);

        if (updateQuestion == null) {
            throw new ResourceNotFoundException("Question is not found");
        }

        updateQuestion.setContent(questionUpdateDTO.getContent());
        updateQuestion.setQuestionType(questionUpdateDTO.getQuestionType());
        updateQuestion.setIsActive(questionUpdateDTO.getIsActive());

        updateQuestion = questionRepository.save(updateQuestion);

        return updateQuestion != null;
    }

    @Override
    public boolean delete(UUID id) {
        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            throw new ResourceNotFoundException("Question is not found");
        }

        questionRepository.delete(question);

        return !questionRepository.existsById(id);
    }

}

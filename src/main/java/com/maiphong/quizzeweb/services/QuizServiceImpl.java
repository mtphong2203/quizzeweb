package com.maiphong.quizzeweb.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.quizzeweb.dtos.quiz.QuizCreateDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizEditDTO;
import com.maiphong.quizzeweb.entities.Quiz;
import com.maiphong.quizzeweb.exceptions.ResourceNotFoundException;
import com.maiphong.quizzeweb.repositories.QuizRepository;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<QuizDTO> getAll() {
        List<Quiz> quizzes = quizRepository.findAll();

        List<QuizDTO> quizDTOs = quizzes.stream().map(quiz -> {
            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setId(quiz.getId());
            quizDTO.setTitle(quiz.getTitle());
            quizDTO.setDescription(quiz.getDescription());
            quizDTO.setDuration(quiz.getDuration());
            quizDTO.setIsActive(quiz.getIsActive());

            return quizDTO;
        }).toList();

        return quizDTOs;
    }

    @Override
    public QuizDTO getById(UUID id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResourceNotFoundException("Quiz is not found");
        }

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setDescription(quiz.getDescription());
        quizDTO.setDuration(quiz.getDuration());
        quizDTO.setIsActive(quiz.getIsActive());

        return quizDTO;

    }

    @Override
    public boolean create(QuizCreateDTO quizCreateDTO) {
        if (quizCreateDTO == null) {
            throw new IllegalArgumentException("Quiz create should not be null");
        }

        Quiz newQuiz = new Quiz();
        newQuiz.setTitle(quizCreateDTO.getTitle());
        newQuiz.setDescription(quizCreateDTO.getDescription());
        newQuiz.setDuration(quizCreateDTO.getDuration());
        newQuiz.setIsActive(quizCreateDTO.getIsActive());

        newQuiz = quizRepository.save(newQuiz);

        return newQuiz != null;
    }

    @Override
    public boolean update(QuizEditDTO quizEditDTO) {
        if (quizEditDTO == null) {
            throw new IllegalArgumentException("Quiz edit should not be null");
        }

        Quiz updateQuiz = quizRepository.findById(quizEditDTO.getId()).orElse(null);

        if (updateQuiz == null) {
            throw new ResourceNotFoundException("Quiz is not found");
        }

        updateQuiz.setTitle(quizEditDTO.getTitle());
        updateQuiz.setDescription(quizEditDTO.getDescription());
        updateQuiz.setDuration(quizEditDTO.getDuration());
        updateQuiz.setIsActive(quizEditDTO.getIsActive());

        updateQuiz = quizRepository.save(updateQuiz);

        return updateQuiz != null;

    }

    @Override
    public boolean delete(UUID id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResourceNotFoundException("Quiz is not exist!");
        }

        quizRepository.delete(quiz);

        return !quizRepository.existsById(id);
    }

}

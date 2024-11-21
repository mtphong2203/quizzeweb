package com.maiphong.quizzeweb.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiphong.quizzeweb.dtos.quiz.QuizCreateDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizDTO;
import com.maiphong.quizzeweb.dtos.quiz.QuizEditDTO;
import com.maiphong.quizzeweb.services.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAll() {
        List<QuizDTO> quizDTOs = quizService.getAll();

        if (quizDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(quizDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getById(@PathVariable UUID id) {
        QuizDTO quizDTO = quizService.getById(id);

        if (quizDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody QuizCreateDTO quizCreateDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        boolean isCreated = quizService.create(quizCreateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody QuizEditDTO quizEditDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        boolean isCreated = quizService.update(quizEditDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = quizService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}

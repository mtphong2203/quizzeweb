package com.maiphong.quizzeweb.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.quizzeweb.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

}

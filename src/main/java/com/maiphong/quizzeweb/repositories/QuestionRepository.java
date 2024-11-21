package com.maiphong.quizzeweb.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.quizzeweb.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

}

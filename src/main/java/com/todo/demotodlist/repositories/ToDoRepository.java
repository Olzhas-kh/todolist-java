package com.todo.demotodlist.repositories;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.demotodlist.models.ToDoModel;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {
    Page<ToDoModel> findByTitleContainingIgnoreCaseAndCreatedDate(Pageable pageable, String title, LocalDate creation_date);
}

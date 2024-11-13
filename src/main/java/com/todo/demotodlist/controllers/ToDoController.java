package com.todo.demotodlist.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.todo.demotodlist.models.ToDoModel;
import com.todo.demotodlist.repositories.ToDoRepository;

import java.time.LocalDate;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8081") 
@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository todoRepository;

    @GetMapping
    public Page<ToDoModel> getTodos(@RequestParam Optional<Integer> page,
                                     @RequestParam Optional<String> title,
                                     @RequestParam Optional<String> date) {
        int pageNumber = page.orElse(0);
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        LocalDate filterDate = date.map(LocalDate::parse).orElse(null);
        return todoRepository.findByTitleContainingIgnoreCaseAndCreatedDate(pageRequest, title.orElse(""), filterDate);
    }

    @PostMapping
    public ToDoModel createTodo(@RequestBody ToDoModel todo) {
        if (todo.getCreatedDate() == null) {
            todo.setCreatedDate(LocalDate.now());
        }
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public ToDoModel updateTodo(@PathVariable Long id, @RequestBody ToDoModel updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(updatedTodo.getTitle());
            todo.setStatus(updatedTodo.isStatus());
            if (updatedTodo.getCreatedDate() != null) {
                todo.setCreatedDate(updatedTodo.getCreatedDate());
            }
            return todoRepository.save(todo);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}

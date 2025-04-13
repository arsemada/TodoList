package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repo.TodoRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<TodoItem> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public TodoItem save(@Valid @NotNull @RequestBody TodoItem todoItem) {
        try {
            return todoRepo.save(todoItem);
        } catch (Exception e) {
            e.printStackTrace(); // Logs the detailed error in the console
            throw e; // Let Spring handle the 500 response
        }
    }

    @PutMapping
    public TodoItem update(@Valid @NotNull @RequestBody TodoItem todoItem){
        return todoRepo.save(todoItem);
    }
}

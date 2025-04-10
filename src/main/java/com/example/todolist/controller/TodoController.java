package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repo.TodoRepo;
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
    public TodoItem save(@com.example.todolist.controller.Valid @NotNull @RequestBody TodoItem todoItem){
        return todoRepo.save(todoItem);
    }
}

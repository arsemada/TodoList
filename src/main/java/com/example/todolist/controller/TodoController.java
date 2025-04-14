package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repo.TodoRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos") // Using a more standard API path
public class TodoController {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodos() {
        List<TodoItem> todos = todoRepo.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        Optional<TodoItem> todo = todoRepo.findById(id);
        return todo.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TodoItem> createTodo(@Valid @RequestBody TodoItem todoItem) {
        TodoItem savedTodo = todoRepo.save(todoItem);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoItem updatedTodo) {
        Optional<TodoItem> existingTodo = todoRepo.findById(id);
        if (existingTodo.isPresent()) {
            updatedTodo.setId(id); // Ensure the ID is set for updating
            TodoItem savedTodo = todoRepo.save(updatedTodo);
            return new ResponseEntity<>(savedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
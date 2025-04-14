package com.example.todolist.controller.ui;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ui/todos")
public class TodoUIController {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoUIController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public String viewTodos(Model model) {
        List<TodoItem> todos = todoRepo.findAll();
        model.addAttribute("todos", todos);
        return "todo-list"; // Name of your HTML template
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String title) {
        TodoItem newTodo = new TodoItem();
        newTodo.setTitle(title);
        todoRepo.save(newTodo);
        return "redirect:/ui/todos"; // Redirect back to the list
    }
}
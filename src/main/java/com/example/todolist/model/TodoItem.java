package com.example.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todos")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private boolean done = false; // Default value for 'done'

    // Default constructor (required by JPA)
    public TodoItem() {
    }

    // Constructor without ID (for creating new items)
    public TodoItem(@NotBlank(message = "Title cannot be blank") String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
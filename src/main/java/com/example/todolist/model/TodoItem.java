package com.example.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TodoItem {

    private Long id;
    @NotBlank
    private String title;
    private boolean done;

    public TodoItem() {
    }

    public TodoItem(Long id, String title, boolean done){
        this.id = id;
        this.title = title;
        this.done = done;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

}

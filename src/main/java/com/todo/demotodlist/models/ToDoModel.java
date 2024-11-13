package com.todo.demotodlist.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todotele")
public class ToDoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate creation_date;
    private LocalDate createdDate;
    private boolean status;

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

    public LocalDate getCreatedDate() {
        return creation_date;
    }
    public LocalDate getCreatedDat() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate creation_date) {
        this.creation_date = creation_date;
    }
    public void setCreatedDat(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package com.example.todo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class TodoTask {

    @PrimaryKey
    int id;

    Date taskDate;



}

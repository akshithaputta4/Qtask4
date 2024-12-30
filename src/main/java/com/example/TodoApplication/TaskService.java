package com.example.TodoApplication;

import java.util.List;

public interface TaskService {

    Todo saveTask(Todo task);
    Todo updateTask(String id,boolean completed);
    List<Todo> getAllTasks();
    void deleteTask(String id);
}

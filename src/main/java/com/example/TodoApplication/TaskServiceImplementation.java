package com.example.TodoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImplementation implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Todo saveTask(Todo task)
    {
        Todo savedTask=taskRepository.save(task);
        return savedTask;
    }
    @Override
    public void deleteTask(String id)
    {
        taskRepository.deleteById(id);
    }
    @Override
    public List<Todo> getAllTasks() {
        return taskRepository.findAll();
    }


    @Override
    public Todo updateTask(String id, boolean completed) {
        Optional<Todo> taskOpt = taskRepository.findById(id);
        if (!taskOpt.isPresent()) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        Todo task = taskOpt.get();
        task.setCompleted(completed);
        return taskRepository.save(task);
    }

}
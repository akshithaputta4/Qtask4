package com.example.TodoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private  TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<Todo> savingTask(@RequestBody Todo task)
    {
        Todo savedTask=taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deletingTask(@PathVariable String id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Todo>> getTasks() {
        List<Todo> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);  
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Todo> updatingTask(@PathVariable String id, @RequestParam boolean completed) {
        try {
            Todo updatedTask = taskService.updateTask(id, completed);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

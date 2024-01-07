package com.dashboard.controller;

import com.dashboard.model.entity.Task;
import com.dashboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(this.taskService.createTask(task), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(this.taskService.getAllTask(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

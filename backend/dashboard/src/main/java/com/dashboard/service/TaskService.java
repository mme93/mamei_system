package com.dashboard.service;

import com.dashboard.repository.TaskRepository;
import com.dashboard.model.entity.Task;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task, HttpServletRequest request) {
        task.setSecurityUserId(Long.valueOf(request.getHeader("userId")));
        task.setUserName(request.getHeader("userName"));
        return taskRepository.save(task);
    }

    public List<Task> getAllTask(HttpServletRequest request) {
        return taskRepository
                .findAll().stream()
                .filter(task -> {
                    System.out.println("UseriD: "+request.getHeader("userId"));
                    if (task.getSecurityUserId() != null && Objects.equals(task.getSecurityUserId(), Long.valueOf(request.getHeader("userId")))) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

}

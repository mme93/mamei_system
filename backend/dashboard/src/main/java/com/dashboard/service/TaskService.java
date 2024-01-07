package com.dashboard.service;

import com.dashboard.repository.TaskRepository;
import com.dashboard.model.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

}

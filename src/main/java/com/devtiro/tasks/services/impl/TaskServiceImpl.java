package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.repos.TaskRepo;
import com.devtiro.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getTasks(UUID taskListId) {
        if (taskListId == null) {
            throw new IllegalArgumentException("taskListId cannot be null");
        }

        return  taskRepo.findByTaskListId(taskListId);
    }
}

package com.devtiro.tasks.controllers;


import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.mappers.TaskMapper;
import com.devtiro.tasks.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task-list-id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> getTasks(@PathVariable("task-list-id") UUID taskListId) {
        List<Task> tasks = taskService.getTasks(taskListId);
        List<TaskDto> taskDtos =tasks.stream()
                .map(taskMapper::fromTask)
                .toList();
        return taskDtos;
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task-list-id") UUID taskListId ,  @RequestBody TaskDto task) {

        Task createdTask = taskService.createTask( taskListId, taskMapper.fromDto(task));
        return taskMapper.fromTask(createdTask);

    }


}

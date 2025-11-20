package com.devtiro.tasks.controllers;


import com.devtiro.tasks.domain.dto.TaskListDto;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.mappers.TaskListMapper;
import com.devtiro.tasks.services.TaskListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;


    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getTaskList() {
            return taskListService.getTaskLists().stream()
                    .map(taskListMapper::toTaskListDto)
                    .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.craeteTaskList(taskListMapper.fromTaskListDto(taskListDto));
        return taskListMapper.toTaskListDto(createdTaskList);
    }

    @GetMapping("/{id}")
    public  TaskListDto getTaskListById(@PathVariable("id") UUID taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId)
                .orElseThrow( () -> new IllegalArgumentException( "TaskList not found with id " + taskListId));
        return taskListMapper.toTaskListDto(taskList);
    }



}

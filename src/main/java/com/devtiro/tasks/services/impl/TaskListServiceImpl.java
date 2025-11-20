package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.repos.TaskListRepo;
import com.devtiro.tasks.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {


    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }


    @Override
    public List<TaskList> getTaskLists() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList craeteTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("Task List already has an id!!");
        } else if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task List title is blank!!");
        }
        LocalDate today = LocalDate.now();
        return taskListRepo.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                today,
                today

        ));


    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepo.findById(id) ;
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (taskListId == null) {
            throw new IllegalArgumentException("Task List id is null!");
        }
        if(!Objects.equals(taskListId, taskList.getId())) {
            throw new IllegalArgumentException("Task List id does not match!");
        }
        TaskList excisedTaskList = taskListRepo.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("Task List does not exist!"));

        excisedTaskList.setTitle(taskList.getTitle());
        excisedTaskList.setDescription(taskList.getDescription());
        excisedTaskList.setUpdateDate(LocalDate.now());
        return taskListRepo.save(excisedTaskList) ;
    }

    @Override
    public void deleteTaskList(UUID id) {
        TaskList excisedTaskList = taskListRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task List does not exist!")
        );
        taskListRepo.delete(excisedTaskList);
    }
}

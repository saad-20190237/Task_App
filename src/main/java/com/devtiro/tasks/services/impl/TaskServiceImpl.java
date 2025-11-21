package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.TaskStatus;
import com.devtiro.tasks.repos.TaskListRepo;
import com.devtiro.tasks.repos.TaskRepo;
import com.devtiro.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskListRepo taskListRepo;

    public TaskServiceImpl(TaskRepo taskRepo, TaskListRepo taskListRepo) {
        this.taskRepo = taskRepo;
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<Task> getTasks(UUID taskListId) {
        if (taskListId == null) {
            throw new IllegalArgumentException("taskListId cannot be null");
        }

        return  taskRepo.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask( UUID taskListId,Task task) {
        TaskList taskList = taskListRepo.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("taskListId " + taskListId + " not found"));

        if (task.getTitle() == null ||task.getTitle().isBlank()) {
            throw new IllegalArgumentException("taskTitle cannot be null or Blank");
        }
        if(task.getPriority() == null){
            throw new IllegalArgumentException("taskStatus cannot be null");
        }

        LocalDate today = LocalDate.now();
        Task newTask = new Task(
               null,
               task.getTitle(),
               task.getDescription(),
               task.getDueDate(),
               TaskStatus.OPEN,
               task.getPriority(),
               today,
               today,
               taskList

       );

        return taskRepo.save(newTask);
    }
}

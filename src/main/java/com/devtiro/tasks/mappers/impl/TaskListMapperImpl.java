package com.devtiro.tasks.mappers.impl;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.domain.dto.TaskListDto;
import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.TaskStatus;
import com.devtiro.tasks.mappers.TaskListMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapperImpl taskMapper;

    public TaskListMapperImpl(TaskMapperImpl taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskList fromTaskListDto(TaskListDto taskListDto) {

        List<Task> tasks = taskListDto.tasks() == null
                ? List.of()
                : taskListDto.tasks()
                .stream()
                .map(taskMapper::fromDto)
                .toList();



        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                tasks,
                null,
                null

        );
    }

    @Override
    public TaskListDto toTaskListDto(TaskList taskList) {

        List<Task> tasks = taskList.getTasks() == null ? List.of() : taskList.getTasks();
        Integer count = tasks.size();
          int counter=0;
        for (Task task : tasks) {
            if(task.getStatus()== TaskStatus.CLOSED)
            {
                counter++;
            }
        }
        Double progress = (double)counter / tasks.size();

        List <TaskDto> taskListDtos = tasks.stream()
                                        .map(taskMapper::fromTask)
                                        .toList();


        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                count,
                progress,
                taskListDtos


        );
    }
}

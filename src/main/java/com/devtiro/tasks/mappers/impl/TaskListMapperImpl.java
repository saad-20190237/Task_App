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

        TaskList taskList = new TaskList();

        taskList.setId(null);

        taskList.setDescription(taskListDto.description());
        taskList.setTitle(taskListDto.title());

        taskList.setTasks(new ArrayList<>());

        if (taskListDto.tasks() != null) {
            for (TaskDto taskDto : taskListDto.tasks()) {
                Task task = taskMapper.fromDto(taskDto);

                task.setId(null);
                taskList.addTask(task);

            }
        }
        return taskList;
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

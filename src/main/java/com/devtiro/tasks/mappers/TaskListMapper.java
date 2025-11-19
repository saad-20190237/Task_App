package com.devtiro.tasks.mappers;

import com.devtiro.tasks.domain.dto.TaskListDto;
import com.devtiro.tasks.domain.entities.TaskList;

public interface TaskListMapper {

   TaskList fromTaskListDto (TaskListDto taskListDto);
   TaskListDto toTaskListDto (TaskList taskList);
}

package com.devtiro.tasks.services;


import com.devtiro.tasks.domain.entities.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskListService {
    List<TaskList> getTaskLists();
    TaskList craeteTaskList(TaskList taskList);

}

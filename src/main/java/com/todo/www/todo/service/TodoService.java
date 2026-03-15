package com.todo.www.todo.service;

import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.dto.TaskDto;
import com.todo.www.todo.dto.TaskUpdateDto;

import java.util.Map;
import java.util.Objects;


public interface TodoService {
    ResponseDto createTask(TaskDto taskDto);
    ResponseDto deleteTask(Integer taskId);
    ResponseDto updateTask(TaskUpdateDto taskUpdateDto);
    ResponseDto getTasks();
}

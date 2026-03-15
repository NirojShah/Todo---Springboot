package com.todo.www.todo.service;

import com.todo.www.todo.TodoRepository;
import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.dto.TaskDto;
import com.todo.www.todo.dto.TaskUpdateDto;
import com.todo.www.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public ResponseDto createTask(TaskDto taskDto) {
        Todo todo = new Todo();

        System.out.println(taskDto.getStatus());
        System.out.println(taskDto.getTodo());

        todo.setStatus(taskDto.getStatus());
        todo.setTodo(taskDto.getTodo());

        System.out.println(todo.getStatus());
        System.out.println(todo.getTodo());

//        return new ResponseDto("failed",HttpStatus.FORBIDDEN,null);

        Todo savedTodo = todoRepository.save(todo);

        return new ResponseDto("success", HttpStatus.CREATED, savedTodo);
    }

    @Override
    public ResponseDto deleteTask(Integer taskId) {
        todoRepository.deleteById(taskId);
        return new ResponseDto("success",HttpStatus.OK,null);
    }

    @Override
    public ResponseDto updateTask(TaskUpdateDto taskUpdateDto) {
        Optional<Todo> task = todoRepository.findById(taskUpdateDto.getTaskId());

        if(task.isEmpty()){
            return new ResponseDto("Task Not found.",HttpStatus.NOT_FOUND,null);
        }

        Todo todo = task.get();
        System.out.println(todo.getStatus());

        return new ResponseDto("Task Updated successfully",HttpStatus.OK,null);
    }

    @Override
    public ResponseDto getTasks() {
        List<Todo> todos = todoRepository.findAll();
        return new ResponseDto("success",HttpStatus.OK,todos);
    }
}

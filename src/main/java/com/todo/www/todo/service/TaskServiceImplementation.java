package com.todo.www.todo.service;

import com.todo.www.todo.TodoRepository;
import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.dto.TaskDto;
import com.todo.www.todo.dto.TaskUpdateDto;
import com.todo.www.todo.entity.Todo;
import com.todo.www.todo.user.UserEntity;
import com.todo.www.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TodoService{

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto createTask(TaskDto taskDto) {
        Todo todo = new Todo();

        Optional<UserEntity> user = userRepository.findById(taskDto.getUserId());

        if(user.isEmpty()){
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,"user is not present");
        }
        UserEntity userInfo = user.get();
        todo.setStatus(taskDto.getStatus());
        todo.setTodo(taskDto.getTodo());
        todo.setUser(userInfo);

        Todo savedTodo = todoRepository.save(todo);

        return new ResponseDto("success", HttpStatus.CREATED, savedTodo);
    }

    @Override
    public ResponseDto deleteTask(Integer taskId) {
        Optional<Todo> todo = todoRepository.findById(taskId);
        if(todo.isEmpty()){
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,null);
        }
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
        if(taskUpdateDto.getTask() != null){
            todo.setTodo(taskUpdateDto.getTask());
        }

        if(taskUpdateDto.getStatus() != null){
            todo.setStatus(taskUpdateDto.getStatus());
        }

        Todo updateTodo = todoRepository.save(todo);

        return new ResponseDto("Task Updated successfully",HttpStatus.OK,updateTodo);
    }

    @Override
    public ResponseDto getTasks(int userId) {
        List<Todo> todos = todoRepository.findByuserId(userId);
        return new ResponseDto("success",HttpStatus.OK,todos);
    }
}

package com.todo.www.todo.controller;

import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.dto.TaskDto;
import com.todo.www.todo.dto.TaskUpdateDto;
import com.todo.www.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public ResponseDto createTodo(@RequestBody TaskDto taskDto){
        System.out.println("TESTING TESTING TESTINNG");
        return todoService.createTask(taskDto);
    }

    @GetMapping("/todos")
    public ResponseDto getTasks(){
        return todoService.getTasks();
    }

    @PutMapping("/todo")
    public ResponseDto updateTask(@RequestBody TaskUpdateDto taskUpdateDto){
        return todoService.updateTask(taskUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteTask(@PathVariable int id){
        return todoService.deleteTask(id);
    }
}

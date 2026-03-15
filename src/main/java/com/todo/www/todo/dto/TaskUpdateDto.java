package com.todo.www.todo.dto;


import com.todo.www.todo.Status;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateDto {
    private int taskId;
    private String task;
    private Status status;
}

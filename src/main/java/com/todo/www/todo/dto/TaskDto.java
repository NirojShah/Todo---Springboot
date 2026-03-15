package com.todo.www.todo.dto;

import com.todo.www.todo.Status;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    private String todo;
    private Status status;
}
package com.todo.www.todo.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateDto {
    private int taskId;
    private String task;
}

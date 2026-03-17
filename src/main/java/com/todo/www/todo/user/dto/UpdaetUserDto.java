package com.todo.www.todo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UpdaetUserDto {
    private String firstName;
    private String lastName;
    private Long phoneNo;
    private String password;
    private String email;
}

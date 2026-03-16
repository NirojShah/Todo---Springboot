package com.todo.www.todo.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDto{
    private String firstName;

    private String lastName;

    private String password;

    private Long phone;

    private String email;

}

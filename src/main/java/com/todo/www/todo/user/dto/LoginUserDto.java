package com.todo.www.todo.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserDto {
    private String email;
    private String password;
}

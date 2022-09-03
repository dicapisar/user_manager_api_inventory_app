package com.dicapisar.userManager.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserCreatedResponse {
    private Long id;
    private String name;
    private String password;
    private Long idRol;
    private String nameRol;
}

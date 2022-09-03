package com.dicapisar.userManager.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserCreateRequest {
    @NotEmpty(message = "The attribute 'name' must not be empty")
    private String name;
    @NotNull(message = "The attribute 'rol' must not be empty")
    private Long rol;
}

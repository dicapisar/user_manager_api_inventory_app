package com.dicapisar.userManager.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoResponse {
    private Long id;
    private String name;
    private String nameRol;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

}

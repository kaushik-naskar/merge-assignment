package com.kaushik.mergeassignment.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;
}

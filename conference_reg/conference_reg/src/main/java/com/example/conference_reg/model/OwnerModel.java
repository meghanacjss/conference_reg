package com.example.conference_reg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerModel {

    private String name;
 
    private String role;

    private String username;
 
    private String password;

    private String email;

}

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

    //    @NotBlank(message = "name must be fill")
//    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    //   @NotBlank(message="role not be blank")
    private String role;
    // @Id
    // @NotBlank(message = "Username not be blank")
    private String username;
    //    @NotBlank(message = "Password not be blank")
//    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;
    //  @Email(message= "email should be in proper format")
    private String email;

}

package com.steliana.springtaskuser.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String firstname;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

}

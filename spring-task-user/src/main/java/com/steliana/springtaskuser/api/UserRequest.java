package com.steliana.springtaskuser.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "First name must not contain special characters.")
    private String firstname;
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Last name must not contain special characters.")
    @NotNull
    private String surname;
    private String address;
    @Pattern( regexp = "^\\+30\\d{10}$", message = "You must own a Greek Phone number in order to proceed.")
    private String phoneNumber;
    @Email(regexp = ".+[@].+[\\.].+", message = "Invalid Mail Format")
    private String email;
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "Invalid ID Format")
    private String personalID;
}

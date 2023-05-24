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

    public UserResponse userResponse(String firstname, String surname, String address, String phoneNumber, String email){
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstname(firstname);
        userResponse.setSurname(surname);
        userResponse.setAddress(address);
        userResponse.setPhoneNumber(phoneNumber);
        userResponse.setEmail(email);

        return userResponse;
    }

}

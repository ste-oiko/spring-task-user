package com.steliana.springtaskuser.api;

import com.steliana.springtaskuser.entity.User;
import com.steliana.springtaskuser.mapper.UserMapper;
import com.steliana.springtaskuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(description = "Retrieves all registered users' attributes")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "All users retrieved"),
            @ApiResponse( responseCode = "404", description = "No user found"),
    })
    @GetMapping(path = "/")
    public ResponseEntity<List<UserResponse>> returnAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Operation(description = "Retrieves user's attributes by ID")
    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "User retrieved by ID"),
            @ApiResponse( responseCode = "404", description = "User ID not found"),
    })
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Operation(description = "Deletes user retrieved by ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "User retrieved by ID deleted"),
            @ApiResponse( responseCode = "404", description = "User ID not found"),
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(description = "Updates user's attributes by ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "User's attributes retrieved by ID updated"),
            @ApiResponse( responseCode = "404", description = "User ID not found"),
    })
    @PutMapping(path = "/{id}")
    @Validated
    public ResponseEntity updateUser(@PathVariable("id") UUID id,@Valid @RequestBody UserRequest user){
            User newUser  = UserMapper.INSTANCE.userRequestToUser(user);
            userService.updateUser(id, newUser);
            return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(description = "Create new user")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "User created"),
    })
    @PostMapping(path = "/")
    @Validated
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest){
              User newUser = UserMapper.INSTANCE.userRequestToUser(userRequest);
              userService.createUser(newUser);
              return new ResponseEntity(HttpStatus.CREATED);
    }


}

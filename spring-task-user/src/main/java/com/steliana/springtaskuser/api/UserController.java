package com.steliana.springtaskuser.api;

import com.steliana.springtaskuser.entity.User;
import com.steliana.springtaskuser.mapper.UserMapper;
import com.steliana.springtaskuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "Get all users")
    @GetMapping(path = "/")
    public ResponseEntity<List<UserResponse>> returnAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Operation(description = "Get user by ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Operation(description = "Delete user by ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(description = "Update user by ID")
    @PutMapping(path = "/{id}")
    @Validated
    public ResponseEntity updateUser(@PathVariable("id") UUID id,@Valid @RequestBody UserRequest user){
            User newUser  = UserMapper.INSTANCE.userRequestToUser(user);
            userService.updateUser(id, newUser);
            return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(description = "Create new user")
    @PostMapping(path = "/")
    @Validated
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest){
              User newUser = UserMapper.INSTANCE.userRequestToUser(userRequest);
              userService.createUser(newUser);
              return new ResponseEntity(HttpStatus.OK);
    }


}

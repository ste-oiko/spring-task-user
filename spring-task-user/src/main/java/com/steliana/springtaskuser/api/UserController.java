package com.steliana.springtaskuser.api;

import com.steliana.springtaskuser.entity.User;
import com.steliana.springtaskuser.exceptions.RecordNotFound;
import com.steliana.springtaskuser.mapper.UserMapper;
import com.steliana.springtaskuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<List<UserResponse>> returnAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateUser(@PathVariable("id") UUID id,@RequestBody UserRequest user){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(user);
        if(violations.isEmpty()) {
            User newUser  = UserMapper.INSTANCE.userRequestToUser(user);
            if (userService.updateUser(id, newUser)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RecordNotFound();
            }
        } else { return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations.iterator().next().getMessage());}
    }

    @PostMapping(path = "/")
    public ResponseEntity createUser(@RequestBody UserRequest user){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(user);

        if(violations.isEmpty()) {
            User newUser = UserMapper.INSTANCE.userRequestToUser(user);
            userService.createUser(newUser);
            return new ResponseEntity(HttpStatus.OK);
        } else { return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations.iterator().next().getMessage());}
    }
}

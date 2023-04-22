package com.steliana.springtaskuser.service;

import com.steliana.springtaskuser.api.UserRequest;
import com.steliana.springtaskuser.api.UserResponse;
import com.steliana.springtaskuser.entity.User;
import com.steliana.springtaskuser.mapper.UserMapper;
import com.steliana.springtaskuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responseUsers = UserMapper.INSTANCE.allUsersToUserResponse(users);
        return responseUsers;
    }

    public UserResponse getById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponse responseUser = UserMapper.INSTANCE.userToUserResponse(user);
            return responseUser;
        } else {
        return null; }
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public boolean updateUser(UUID id, User newData) {
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()){
            User newUser = oldUser.get();
            newUser.setAddress(newData.getAddress());
            newUser.setFirstname(newData.getFirstname());
            newUser.setEmail(newData.getEmail());
            newUser.setSurname(newData.getSurname());
            newUser.setPersonalID(newData.getPersonalID());
            newUser.setPhoneNumber(newData.getPhoneNumber());
            userRepository.save(newUser);
            return true; } else { return false; }
    }

    public void createUser(User user) {

            user.setDate();
            userRepository.save(user);
    }
}

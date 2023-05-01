package com.steliana.springtaskuser.service;

import com.steliana.springtaskuser.api.UserResponse;
import com.steliana.springtaskuser.entity.User;
import com.steliana.springtaskuser.exceptions.RecordNotFound;
import com.steliana.springtaskuser.mapper.UserMapper;
import com.steliana.springtaskuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        if( !users.isEmpty() ) {
            return UserMapper.INSTANCE.allUsersToUserResponse(users);
        } else {
            throw new RecordNotFound();
        }
    }

    public UserResponse getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(RecordNotFound::new);
        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    public void deleteById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(RecordNotFound::new);
        userRepository.deleteById(id);
    }

    public void updateUser(UUID id, User newData) {
        User oldUser = userRepository.findById(id).orElseThrow(RecordNotFound::new);
        oldUser.setAddress(newData.getAddress());
        oldUser.setFirstname(newData.getFirstname());
        oldUser.setEmail(newData.getEmail());
        oldUser.setSurname(newData.getSurname());
        oldUser.setPersonalID(newData.getPersonalID());
        oldUser.setPhoneNumber(newData.getPhoneNumber());
        userRepository.save(oldUser);
    }

    public void createUser(User user) {

            user.setDate();
            userRepository.save(user);
    }
}

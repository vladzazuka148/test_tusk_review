package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateUserRequest;
import org.example.dto.UpdateUserRequest;
import org.example.dto.UserRest;
import org.example.exception.NotFoundException;
import org.example.model.entity.User;
import org.example.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserRest createUser(CreateUserRequest request) {
        log.info("Start creating user");

        User user = new User();
        user.setAge(request.getAge());
        user.setName(request.getName());

        User saved = userRepository.save(user);

        log.info("User was created");
        return new UserRest(saved);
    }


    public UserRest getUserById(UUID userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return new UserRest(user);
    }

    public UserRest updateUser(UUID userId, UpdateUserRequest request) throws NotFoundException {
        log.info("Start of updating user with id {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setName(request.getName());
        user.setAge(request.getAge());
        userRepository.save(user);

        log.info("User with id {} was updated", userId);
        return new UserRest(user);
    }

    public void deleteUserById(UUID userId) throws NotFoundException {
        log.info("Start deleting user with id {}", userId);

        User toDelete = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(toDelete);

        log.info("User with id {} was deleted", userId);
    }

    public void checkUser(UUID userId) throws NotFoundException {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new NotFoundException("No such user");
        }
    }
}

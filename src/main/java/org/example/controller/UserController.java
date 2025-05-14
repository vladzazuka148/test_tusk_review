package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateUserRequest;
import org.example.dto.ResultRest;
import org.example.dto.UpdateUserRequest;
import org.example.dto.UserRest;
import org.example.exception.NotFoundException;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRest> postUser(@RequestBody CreateUserRequest request) {
        UserRest result = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRest> getUserInfo(@PathVariable("id") UUID userId) throws NotFoundException {
        UserRest result = userService.getUserById(userId);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRest> putUser(@PathVariable("id") UUID userId,
                                            @RequestBody UpdateUserRequest request) throws NotFoundException {

        UserRest result = userService.updateUser(userId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultRest> deleteUser(@PathVariable("id") UUID userId) throws NotFoundException {
        userService.deleteUserById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResultRest.stub());
    }


}

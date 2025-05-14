package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DigitalServiceRest;
import org.example.dto.ResultRest;
import org.example.dto.SubscriptionRest;
import org.example.exception.DuplicateException;
import org.example.exception.NotFoundException;
import org.example.service.DigitalServiceService;
import org.example.service.UserService;
import org.example.service.UserSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;
    private final DigitalServiceService digitalServiceService;
    private final UserService userService;

    @PostMapping("/{user_id}/subscriptions/{sub_id}")
    public ResponseEntity<SubscriptionRest> postSubscription(@PathVariable("user_id") UUID userId,
                                                             @PathVariable("sub_id") UUID subId) throws NotFoundException, DuplicateException {

        userService.checkUser(userId);
        digitalServiceService.checkDigitalService(subId);

        SubscriptionRest result = userSubscriptionService.addSubscriptionToUserById(subId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{user_id}/subscriptions")
    public ResponseEntity<List<DigitalServiceRest>> getUserSubscriptions(@PathVariable("user_id") UUID userId) throws NotFoundException {

        userService.checkUser(userId);

        List<UUID> userSubscriptionsIds = userSubscriptionService.getUserSubscriptionsIdsByUserId(userId);
        List<DigitalServiceRest> result = digitalServiceService.getByIds(userSubscriptionsIds);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{user_id}/subscriptions/{sub_id}")
    public ResponseEntity<ResultRest> deleteUserSubscription(@PathVariable("user_id") UUID userId,
                                                             @PathVariable("sub_id") UUID subId) throws NotFoundException {
        userSubscriptionService.deleteUserSubscription(userId, subId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResultRest.stub());
    }
}

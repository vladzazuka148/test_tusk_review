package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateDigitalServiceRequest;
import org.example.dto.CreateUserRequest;
import org.example.dto.DigitalServiceRest;
import org.example.dto.ResultRest;
import org.example.dto.UserRest;
import org.example.exception.DuplicateException;
import org.example.exception.NotFoundException;
import org.example.service.DigitalServiceService;
import org.example.service.UserSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/digital-services")
@RequiredArgsConstructor
public class DigitalServiceController {

    private final DigitalServiceService digitalServiceService;
    private final UserSubscriptionService userSubscriptionService;

    @PostMapping
    public ResponseEntity<DigitalServiceRest> postDigitalService(@RequestBody CreateDigitalServiceRequest request) throws DuplicateException {
        DigitalServiceRest result = digitalServiceService.createDigitalService(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalServiceRest> getDigitalServiceInfo(@PathVariable("id") UUID digitalServiceId) throws NotFoundException {
        DigitalServiceRest result = digitalServiceService.getById(digitalServiceId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/top")
    public ResponseEntity<List<DigitalServiceRest>> getTop3DigitalServices() throws NotFoundException {
        List<UUID> top3UserSubscriptionsIds = userSubscriptionService.getTop3UserSubscriptions();

        List<DigitalServiceRest> result = digitalServiceService.getByIds(top3UserSubscriptionsIds);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultRest> deleteDigitalService(@PathVariable("id") UUID digitalServiceId) throws NotFoundException {
        digitalServiceService.deleteById(digitalServiceId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResultRest.stub());
    }
}

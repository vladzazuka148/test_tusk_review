package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.SubscriptionRest;
import org.example.exception.DuplicateException;
import org.example.exception.NotFoundException;
import org.example.model.entity.Subscription;
import org.example.model.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;

    public SubscriptionRest addSubscriptionToUserById(UUID subId, UUID userId) throws DuplicateException {
        log.info("Start adding subscription with id {} for user with id {}", subId, userId);

        Subscription.Pk pk = new Subscription.Pk(userId, subId);

        boolean subscriptionAlreadyExists = userSubscriptionRepository.existsById(pk);

        if (subscriptionAlreadyExists) {
            log.error("Subscription with id {} already exists for user with id {}", subId, userId);

            throw new DuplicateException("Subscription already exists for user");
        } else {
            Subscription saved = userSubscriptionRepository.save(new Subscription(pk));

            log.info("Subscription with id {} was added to user with id {}", subId, userId);
            return new SubscriptionRest(saved);
        }
    }

    public List<UUID> getUserSubscriptionsIdsByUserId(UUID userId) {
        return userSubscriptionRepository.findUserSubscriptionsIdsByUserId(userId);
    }

    public void deleteUserSubscription(UUID userId, UUID subId) throws NotFoundException {
        log.info("Start deleting subscription with id {} from user with id {}", subId, userId);

        Subscription.Pk pk = new Subscription.Pk(userId, subId);

        Subscription toDelete = userSubscriptionRepository.findById(pk)
                .orElseThrow(() -> new NotFoundException("Subscription for user not found"));

        userSubscriptionRepository.delete(toDelete);

        log.info("Subscription with id {} was deleted from user with id {}", subId, userId);
    }

    public List<UUID> getTop3UserSubscriptions() throws NotFoundException {
        List<Subscription> all = userSubscriptionRepository.findAll();

        if (all.isEmpty()) {
            throw new NotFoundException("User subscriptions not found for build top list");
        }

        return userSubscriptionRepository.findTop3UserSubscriptionsIds();
    }
}

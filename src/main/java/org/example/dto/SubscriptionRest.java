package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.entity.Subscription;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionRest {

    private UUID userId;
    private UUID digitalServiceId;

    public SubscriptionRest(Subscription subscription) {
        this.userId = subscription.getId().getUserId();
        this.digitalServiceId = subscription.getId().getDigitalServiceId();
    }
}

package org.example.model.repository;

import org.example.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<Subscription, Subscription.Pk> {

    @Query("""
            SELECT s.id.digitalServiceId FROM Subscription s
            WHERE s.id.userId = :userId
            """)
    List<UUID> findUserSubscriptionsIdsByUserId(@Param("userId") UUID userId);

    @Query("""
            SELECT s.id.digitalServiceId FROM Subscription s
            GROUP BY s.id.digitalServiceId
            ORDER BY COUNT(s.id.digitalServiceId) DESC
            LIMIT 3
            """)
    List<UUID> findTop3UserSubscriptionsIds();
}

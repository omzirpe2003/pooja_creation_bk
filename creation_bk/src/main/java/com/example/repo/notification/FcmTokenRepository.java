package com.example.repo.notification;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.notification.FcmToken;



@Repository
public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    Optional<FcmToken> findByUserIdAndType(String userId, String type);
}
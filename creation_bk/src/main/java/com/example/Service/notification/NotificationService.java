package com.example.Service.notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.example.Model.notification.FcmToken;
import com.example.repo.notification.FcmTokenRepository;
@Service
public class NotificationService {

	
	
	private final FirebaseMessaging firebaseMessaging;
	private final FcmTokenRepository tokenRepository;

	public NotificationService(FirebaseMessaging firebaseMessaging,
			 FcmTokenRepository tokenRepository) {
	       this.firebaseMessaging = firebaseMessaging;
	       this.tokenRepository = tokenRepository;
	}

    public void sendToUser(String userId, String title, String body, Map<String, String> data) {
        sendNotification(userId, "user", title, body, data);
    }

    public void sendToVendor(String vendorId, String title, String body, Map<String, String> data) {
        sendNotification(vendorId, "vendor", title, body, data);
    }

    private void sendNotification(String entityId, String type,
                                  String title, String body,
                                  Map<String, String> data) {

        Optional<FcmToken> tokenOpt = tokenRepository.findByUserIdAndType(entityId, type);
        if (tokenOpt.isEmpty()) {
            System.out.println("No FCM token found for " + type + ": " + entityId);
            return;
        }

        String token = tokenOpt.get().getToken();

        try {
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .putAllData(data != null ? data : new HashMap<>())
                    .setToken(token)
                    .build();

            String response = firebaseMessaging.send(message);
            System.out.println("Sent notification: " + response);

        } catch (FirebaseMessagingException e) {
            if (e.getMessagingErrorCode() == MessagingErrorCode.INVALID_ARGUMENT ||
                //e.getMessagingErrorCode() == MessagingErrorCode.NOT_FOUND ||
                e.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) {
                // Token invalid → remove it
                tokenRepository.delete(tokenOpt.get());
                System.out.println("Removed invalid token for " + entityId);
            } else {
                System.err.println("Failed to send notification: " + e.getMessage());
            }
        }
    }
}
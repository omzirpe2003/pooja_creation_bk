package com.example.controller.notification;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Model.notification.FcmToken;
import com.example.Model.notification.TestNotification;
import com.example.Model.notification.TokenRequest;
import com.example.Service.notification.NotificationService;
import com.example.repo.notification.FcmTokenRepository;




@RestController
@RequestMapping("/api/fcm")
public class NotificationController {

    private final NotificationService notificationService;
    private final FcmTokenRepository tokenRepository;

    public NotificationController(
            NotificationService notificationService,
            FcmTokenRepository tokenRepository) {
        this.notificationService = notificationService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/token")
    public ResponseEntity<String> registerToken(@RequestBody TokenRequest request) {
        FcmToken token = new FcmToken();
        token.setUserId(request.getUserId());
        token.setToken(request.getToken());
        token.setType(request.getType());
        token.setDevice(request.getDevice());

        tokenRepository.save(token);
        return ResponseEntity.ok("Token registered/updated");
    }

    @PostMapping("/test")
    public ResponseEntity<String> sendTestNotification(@RequestBody TestNotification dto) {
        Map<String, String> data = new HashMap<>();
        data.put("orderId", "TEST_123");
        data.put("type", "order_update");

        if ("user".equalsIgnoreCase(dto.getTargetType())) {
            notificationService.sendToUser(
                dto.getTargetId(),
                dto.getTitle(),
                dto.getBody(),
                data
            );
        } else {
            notificationService.sendToVendor(
                dto.getTargetId(),
                dto.getTitle(),
                dto.getBody(),
                data
            );
        }

        return ResponseEntity.ok("Test notification sent");
    }
}

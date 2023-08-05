package com.mst312.notification;

import com.mah312.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        NotificationHistory notificationHistory = NotificationHistory
                .builder()
                .customerEmail(notificationRequest.customerEmail())
                .customerId(notificationRequest.customerId())
                .sender("Mahdi Mostafavi")
                .sentAt(LocalDateTime.now())
                .message(notificationRequest.message())
                .build();

        notificationRepository.save(notificationHistory);
    }
}

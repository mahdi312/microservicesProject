package com.mst312.notification;

import com.mah312.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void send(@RequestBody NotificationRequest notificationRequest) {
        log.info("notification sent by {} at {} for customer id {} to customer email {}",
                "Mahdi", LocalDateTime.now(), notificationRequest.customerId(), notificationRequest.customerEmail());
        notificationService.send(notificationRequest);
    }
}


package com.mst312.notification.rabbitmq;

import com.mah312.clients.notification.NotificationRequest;
import com.mst312.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("consumed {} from the queue", notificationRequest);
        notificationService.send(notificationRequest);
    }

}

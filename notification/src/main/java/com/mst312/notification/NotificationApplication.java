package com.mst312.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.mst312.notification",
        "com.mah312.amqp"
})
@EnableFeignClients(basePackages = "com.mah312.clients")
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer rabbitMQMessageProducer,
//            NotificationConfig notificationConfig
//    ){
//        return args -> {
//            rabbitMQMessageProducer.publish(
//                    "mahdi",
//                    notificationConfig.getInternalExchanges(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
}
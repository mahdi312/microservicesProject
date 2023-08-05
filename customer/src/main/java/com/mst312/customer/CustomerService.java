package com.mst312.customer;

import com.mah312.amqp.producer.RabbitMQMessageProducer;
import com.mah312.clients.fraud.FraudCheckResponse;
import com.mah312.clients.fraud.FraudClient;
import com.mah312.clients.notification.NotificationQueue;
import com.mah312.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    //    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

//    public static final String FRAUDSTER_CHECKING_URL = "http://FRAUD/api/v1/fraud-check/{customerId}";

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        //fraudster checking
        FraudCheckResponse customerFraudster = fraudClient.isCustomerFraudster(customer.getId());

/*      //fraudster checking in old way
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                FRAUDSTER_CHECKING_URL,
                FraudCheckResponse.class,
                customer.getId()
        );
*/

        //send notification
        NotificationRequest notificationRequest = NotificationRequest
                .builder()
                .customerEmail(customer.getEmail())
                .customerId(customer.getId())
                .message("don't worry, everything is OK..customer is registered.")
                .build();

        rabbitMQMessageProducer.publish(
                notificationRequest,
                NotificationQueue.NOTIFICATION_EXCHANGE,
                NotificationQueue.NOTIFICATION_INTERNAL_EXCHANGE_ROUTING_KEY
        );

        if (customerFraudster.isFraudster()) {
            throw new IllegalStateException("the customer is fraudster");
        }

    }
}

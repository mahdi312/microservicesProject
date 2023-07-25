package com.mst312.customer;

import com.mah312.clients.fraud.FraudCheckResponse;
import com.mah312.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    //    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

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

//        //fraudster checking
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                FRAUDSTER_CHECKING_URL,
//                FraudCheckResponse.class,
//                customer.getId()
//        );


        if (customerFraudster.isFraudster()) {
            throw new IllegalStateException("the customer is fraudster");
        }

    }
}

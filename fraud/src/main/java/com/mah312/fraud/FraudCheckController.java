package com.mah312.fraud;

import com.mah312.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isCustomerFraudster(@PathVariable("customerId") Long customerId) {
        Boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud checking for customer {}", customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }
}

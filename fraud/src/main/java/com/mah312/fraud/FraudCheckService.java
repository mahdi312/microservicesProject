package com.mah312.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public Boolean isFraudulentCustomer(Long customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .createdAt(LocalDateTime.now())
                        .isFraudster(false)
                        .customerId(customerId)
                        .build()
        );
        return false;
    }

}

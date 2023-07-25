package com.mah312.fraud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(sequenceName = "fraud_id_sequence",
            name = "fraud_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_id_sequence")
    private Long id;

    private Long customerId;

    private Boolean isFraudster;

    private LocalDateTime createdAt;
}

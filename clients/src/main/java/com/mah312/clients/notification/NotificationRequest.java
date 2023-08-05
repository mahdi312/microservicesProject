package com.mah312.clients.notification;

import lombok.Builder;

@Builder
public record NotificationRequest(String customerEmail, Long customerId, String message) {
}

package com.mst312.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:sensitive-information/user-pass-env.properties", ignoreResourceNotFound = true)
public class NotificationEnvironmentConfig {
}

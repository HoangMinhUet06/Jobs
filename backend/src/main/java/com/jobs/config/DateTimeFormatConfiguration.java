package com.jobs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration class to customize date/time formatting across the application
// This ensures consistent date/time format in REST API requests/responses
@Configuration
public class DateTimeFormatConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Register custom date/time formatters for parsing request parameters
        // and formatting response data
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();

        // Use ISO format for date/time (e.g., 2024-01-17T10:30:00)
        // This is the standard format for APIs
        registrar.setUseIsoFormat(true);

        // Apply the formatters to the registry
        registrar.registerFormatters(registry);
    }
}

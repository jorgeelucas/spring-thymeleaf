package br.com.letscode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.letscode.service.EmailService;

@Configuration
public class SpringConfiguration {

    @Bean
    public EmailService emailService() {
        return new EmailService("877", "654");
    }

}

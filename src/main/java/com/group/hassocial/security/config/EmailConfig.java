package com.group.hassocial.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.Properties;

@Configuration
public class EmailConfig {

    public static JavaMailSenderImpl javaMailProperties(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("ozdizdar.baris@gmail.com");
        mailSender.setPassword("Yengeckova12");
        return mailSender;
    }
    @Bean
    public Session getJavaMailSender()
    {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.host", javaMailProperties().getHost());
        properties.setProperty("mail.smtp.port", String.valueOf(javaMailProperties().getPort()));
        properties.setProperty("mail.smtp.user", javaMailProperties().getUsername());
        properties.setProperty("mail.smtp.password", javaMailProperties().getPassword());
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties);

        javaMailProperties().setJavaMailProperties(properties);
        return session;
    }
}

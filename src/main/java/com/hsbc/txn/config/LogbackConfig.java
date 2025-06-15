package com.hsbc.txn.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {
    @Bean
    public ConsoleAppender<ILoggingEvent> consoleAppender() {
        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} - %msg%n");
        encoder.setContext(appender.getContext());
        encoder.start();
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }

    @Bean
    public Logger logbackLevelConfig() {
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.DEBUG);
         return rootLogger;
    }
}

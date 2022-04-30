package com.zem.diveschool.config;

import com.zem.diveschool.persistence.services.ImAliveManager;
import com.zem.diveschool.persistence.services.impl.ImAliveManagerImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifySpringConfig {

    @Bean(name="imAliveService")
    public ImAliveManager helloWorld()
    {
        return new ImAliveManagerImpl();
    }
}

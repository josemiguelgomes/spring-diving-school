package com.zem.diveschool.config;

import com.zem.diveschool.persistence.services.ImAliveManager;
import com.zem.diveschool.persistence.services.impl.ImAliveManagerImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan({ "com.zem.dive.school.persistence.service" })
public class VerifySpringConfig {

    @Bean(name="imAliveService")
    public ImAliveManager helloWorld()
    {
        return new ImAliveManagerImpl();
    }
}

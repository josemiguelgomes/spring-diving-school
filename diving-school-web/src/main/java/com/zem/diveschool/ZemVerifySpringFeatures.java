package com.zem.diveschool;

import com.zem.diveschool.config.VerifySpringConfig;
import com.zem.diveschool.persistence.services.ImAliveManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ZemVerifySpringFeatures {

    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(VerifySpringConfig.class);

        ImAliveManager obj = (ImAliveManager) context.getBean("imAliveService");

        System.out.println( obj.getServiceName() );
    }
}

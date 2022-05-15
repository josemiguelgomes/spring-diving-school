package com.zem.diveschool.config;

import com.zem.diveschool.converters.ConverterDtoEntityService;
import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add((Converter) new CardConverter());
//        converters.add(new MapToStringConverter());
//        converters.add(new StringToMapConverter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
*/

}

package com.example.springbootnouniquebeandefinitionexception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by mtumilowicz on 2018-11-15.
 */
@Component
public class Producer {

    @Bean("first")
    public String getFirst() {
        return "first";
    }

    @Bean
    @QualifierForSecondString
    public String getSecond() {
        return "second";
    }

    @Bean
    @Primary
    public String getThird() {
        return "third";
    }
}

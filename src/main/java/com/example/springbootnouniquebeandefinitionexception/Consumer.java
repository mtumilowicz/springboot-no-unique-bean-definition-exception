package com.example.springbootnouniquebeandefinitionexception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by mtumilowicz on 2018-11-15.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class Consumer {
    String first;
    String second;
    
    public Consumer(@Qualifier("first") String first, @QualifierForSecondString String second) {
        this.first = first;
        this.second = second;
    }
}

package com.example.springbootnouniquebeandefinitionexception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootNoUniqueBeanDefinitionExceptionApplicationTests {

    @Autowired
    Consumer consumer;

    @Test
    public void contextLoads() {
        assertThat(consumer.getFirst(), is("first"));
        assertThat(consumer.getSecond(), is("second"));
    }

}

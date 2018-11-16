# springboot-no-unique-bean-definition-exception
How to deal with NoUniqueBeanDefinitionException.

_Reference: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core

# preface
`@Autowired` is fundamentally about type-driven injection with 
optional semantic qualifiers. This means that qualifier values, 
even with the bean name fallback, always have narrowing semantics 
within the set of type matches. 

Because autowiring by type may lead to multiple candidates
(`NoUniqueBeanDefinitionException`), 
it is often necessary to have more control over the selection 
process. One way to accomplish this is with Spring’s `@Primary` 
annotation. `@Primary` indicates that a particular bean should 
be given preference when multiple beans are candidates to be 
autowired to a single-valued dependency.

When you need more control over the selection process, 
you can use Spring’s `@Qualifier` annotation. You can associate 
qualifier values with specific arguments, narrowing the set of 
type matches so that a specific bean is chosen for each argument. 

**The name of the Qualifier should be assigned in a 
business logic sense but not in implementation sense.** 

# digression
If you intend to express annotation-driven injection by name, 
do not primarily use `@Autowired`, even if it is capable of selecting by 
bean name among type-matching candidates. Instead, use the JSR-250 
`@Resource` annotation, which is semantically defined to identify a specific 
target component by its unique name, with the declared type being irrelevant 
for the matching process. `@Autowired` has rather different semantics: After 
selecting candidate beans by type, the specified String qualifier value is 
considered within those type-selected candidates only (for example, matching an 
account qualifier against beans marked with the same qualifier label).

# project description
We are giving examples of producing beans with:
* `@Primary`
* `@Qualifier`
* `@Bean` with name attribute
and injecting them (using @Qualifier or default)

## details
* we have `Producer` class, which produces beans:
    ```
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
    ```
* we have `Consumer` class with fields:
    ```
    String first;
    String second;
    String third;    
    ```
    and injection by constructor:
    ```
    public Consumer(@Qualifier("first") String first, 
                    @QualifierForSecondString String second,
                    String third) {
            this.first = first;
            this.second = second;
            this.third = third;
    }    
    ```
# tests
In `SpringbootNoUniqueBeanDefinitionExceptionApplicationTests` we 
verify if all injections were made without errors:
```
@Autowired
Consumer consumer;

@Test
public void contextLoads() {
    assertThat(consumer.getFirst(), is("first"));
    assertThat(consumer.getSecond(), is("second"));
    assertThat(consumer.getThird(), is("third"));
}
```
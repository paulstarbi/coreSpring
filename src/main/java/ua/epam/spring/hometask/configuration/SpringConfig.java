package ua.epam.spring.hometask.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = "ua.epam.spring.hometask")
@EnableAspectJAutoProxy
public class SpringConfig {

}

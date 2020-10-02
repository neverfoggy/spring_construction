package com.kursach.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }

    @Bean
    public SpringTemplateEngine templateEngine() {

        var templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new SpringDataDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.setTemplateResolver(thirdTemplateResolver());

        return templateEngine;
    }

    @Bean
    public ClassLoaderTemplateResolver thirdTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }


}

package pl.szotaa.torrentr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class overrides default Spring MVC configuration.
 *
 * @author Jakub Szota
 *
 */

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry
                .addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
    }
}

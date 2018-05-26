package pl.szotaa.torrentr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring's MVC configuration.
 *
 * @author Jakub Szota
 */

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Configures simple automated controllers.
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addViewController("/").setViewName("index");
    }

    /**
     * Adds handlers to serve static resources.
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry
                .addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry
                .addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
    }
}

package sg.edu.nus.comp.cs5331ssti;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreeMarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        // Set the FreeMarker template loader
        configurer.setTemplateLoaderPath("classpath:/templates/");

        // Set the failOnMissingTemplate property to true
        Properties properties = new Properties();
        properties.setProperty("template_exception_handler", "rethrow");
        properties.setProperty("default_encoding", "UTF-8");
        properties.setProperty("url_escaping_charset", "UTF-8");

        // Disallow non-existent template
        //properties.setProperty("fail_on_missing_template", "true");

        // Do not load any template classes (Not working)
        properties.setProperty("template_class_resolver", "freemarker.core.TemplateClassResolver.ALLOWS_NOTHING_RESOLVER");

        configurer.setFreemarkerSettings(properties);


        return configurer;
    }
}
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
        configurer.setTemplateLoaderPath("classpath:/templates");

        // Set ALLOW_NOTHING_RESOLVER to block freemarker.template.utility.Execute instantiating
        Properties settings = new Properties();
        //settings.setProperty("new_builtin_class_resolver", "allows_nothing"); // Comment to disable setting
        configurer.setFreemarkerSettings(settings);

        return configurer;
    }
}
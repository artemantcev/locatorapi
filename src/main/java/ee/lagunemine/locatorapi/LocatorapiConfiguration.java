package ee.lagunemine.locatorapi;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class LocatorapiConfiguration {
    private static final String LOGGER_NAME = "application";

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger(LOGGER_NAME);
    }
}

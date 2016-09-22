package com.example.app;

import com.example.domain.DomainService;
import com.example.domain.MessageService;
import com.example.rest.ExampleRestInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class ApplicationBootstrap {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationBootstrap.class);

    private static final Map<Class, DomainService> services = new HashMap<>();

    public static void main(String[] args) {

        printApplicationAsciiBanner("banner.txt");

        final ApplicationContext context = createSpringContext();

        services.put(MessageService.class, context.getBean(MessageService.class));

        ExampleRestInitializer.initialize(services);
    }

    private static ApplicationContext createSpringContext() {
        return new AnnotationConfigApplicationContext(
                ExampleDomainConfig.class
//                ,
//                IntegrationConfig.class,
//                PersistenceConfig.class
        );
    }

    private static void printApplicationAsciiBanner(final String fileName) {

        try {

            InputStream inputStream = ApplicationBootstrap.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);

            br.close();

        } catch (Exception e) {
            logger.warn("Could not display application ascii banner.", e);
        }
    }
}

package hello;

import java.io.File;

import org.apache.catalina.session.FileStore;
import org.apache.catalina.session.PersistentManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static final Log log = LogFactory.getLog(Application.class);

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return (WebServerFactoryCustomizer<TomcatServletWebServerFactory>) factory -> {
            TomcatServletWebServerFactory containerFactory = (TomcatServletWebServerFactory) factory;
            containerFactory.addContextCustomizers(context -> {
                final PersistentManager persistentManager = new PersistentManager();
                final FileStore store = new FileStore();

                final String sessionDirectory = makeSessionDirectory();
                log.info("Writing sessions to " + sessionDirectory);
                store.setDirectory(sessionDirectory);

                persistentManager.setStore(store);
                context.setManager(persistentManager);
            });
        };
    }

    private static String makeSessionDirectory() {
        final String cwd = System.getProperty("user.dir");
        return cwd + File.separator + "sessions";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

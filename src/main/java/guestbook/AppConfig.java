package guestbook;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

@Configuration
public class AppConfig {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Bean
    public UserService userService() {
        log.info("Getting UserService");
        return UserServiceFactory.getUserService();
    }

    @Bean
    public PersistenceManagerFactory pmFactory() {
        log.info("Getting PersistenceManagerFactory");
        return JDOHelper.getPersistenceManagerFactory("transactions-optional");
    }
}

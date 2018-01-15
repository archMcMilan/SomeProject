package kiev.ua;

import kiev.ua.service.StoreService;
import kiev.ua.service.impl.StoreServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MyTestsConfiguration {

    @Bean
    public StoreService storeService() {
        return new StoreServiceImpl();
    }
}

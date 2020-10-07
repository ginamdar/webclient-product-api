package webclient.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientFactory implements FactoryBean<WebClient> {
    @Override
    public WebClient getObject() throws Exception {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/products")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return WebClient.class;
    }
}

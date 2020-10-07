package webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import webclient.factory.WebClientFactory;

@Configuration
@ComponentScan(basePackages = "webclient")
public class ApplicationConfig {

    @Bean
    public WebClientFactory webClientFactory() {
        return new WebClientFactory();
    }
    @Bean
    public WebClient webClient() throws Exception{
        return webClientFactory().getObject();
    };
}

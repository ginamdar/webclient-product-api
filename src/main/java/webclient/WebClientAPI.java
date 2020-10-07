package webclient;

import model.Product;
import model.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class WebClientAPI {

//    @Bean
//    WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl("http://localhost:8081/products")
//                .defaultHeader("Content-Type", "application/json")
//                .build();
//    };

    @Autowired
    private WebClient webClient;

    WebClientAPI() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081/products")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<ResponseEntity<Product>> postNewProduct() {
        return webClient.post()
                .body(Mono.just(new Product(null, "Black Tea", 1.99)), Product.class)
                .exchange()
                .flatMap(clientResponse -> clientResponse.toEntity(Product.class))
                .doOnSuccess(o -> System.out.println("**********POST " + o));
    }

    public Flux<Product> getAllProducts() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext(o -> System.out.println("*************GET " + o));
    }

    public Flux<ProductEvent> getAllEvents() {
        return webClient.get()
                .uri("/events")
                .retrieve()
                .bodyToFlux(ProductEvent.class);
    }


}

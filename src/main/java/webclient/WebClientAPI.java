package webclient;

import model.Product;
import model.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component(value="webClientAPI")
public class WebClientAPI {

    private WebClient webClient;

    WebClientAPI(WebClient webClient) {
        this.webClient = webClient;
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

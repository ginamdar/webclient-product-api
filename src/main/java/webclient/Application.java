package webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

    public static void main(String args[]) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        WebClientAPI api = applicationContext.getBean("webClientAPI", WebClientAPI.class);

        System.out.println(api);
        api.postNewProduct()
                .thenMany(api.getAllProducts())
                .take(1)
                .thenMany(api.getAllEvents())
                .subscribe(System.out::println);
//        Thread.sleep(3000);
    }

}

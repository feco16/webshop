package org.webshop.productmanagement.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ProductControllerTest {

//    @Container
//    private static MariaDBContainer mariaDBContainer = new MariaDBContainer<>("mariadb:10.3.32");

    @LocalServerPort
    private int port;

    private final WebClient webClient = WebClient.create();

//    @DynamicPropertySource
//    public  static void overrideProps(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
//        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
//    }

    @Test
    public void getStocks_validToken_returnsAll() {
        // given

        // when
        List<?> result = webClient.post()
                .uri("http://localhost:" + port + "/products/filter")
                .retrieve().bodyToMono(List.class).block();
        // then

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }
}

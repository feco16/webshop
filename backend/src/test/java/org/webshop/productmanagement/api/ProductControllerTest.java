package org.webshop.productmanagement.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.webshop.common.to.FilterAndPageable;
import org.webshop.common.to.IdResponse;
import org.webshop.common.to.RestPageImpl;
import org.webshop.to.CreateProductTO;
import org.webshop.to.ProductFilterTO;
import org.webshop.to.ProductTO;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ProductControllerTest {

    @Container
    private static MariaDBContainer mariaDBContainer = new MariaDBContainer<>("mariadb:10.3.32");

    @LocalServerPort
    private int port;

    private final WebClient webClient = WebClient.create();
    final ParameterizedTypeReference<RestPageImpl<ProductTO>> productTOType = new ParameterizedTypeReference<>() {
    };

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
    }

    @Test
    public void getStocks_validToken_returnsAll() {
        // given
        final long id1 = createProduct("Product 1");
        final long id2 = createProduct("Product 2");
        final ProductFilterTO productFilterTO = new ProductFilterTO();
        final FilterAndPageable<ProductFilterTO> filterAndPageable = new FilterAndPageable<>(productFilterTO);

        // when
        final RestPageImpl<ProductTO> result = webClient.post()
                .uri("http://localhost:" + port + "/products/filter")
                .body(Mono.just(filterAndPageable), FilterAndPageable.class)
                .retrieve()
                .bodyToMono(productTOType).block();

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().size()).isEqualTo(2);
    }

    private long createProduct(final String name) {
        final CreateProductTO createProductTO = CreateProductTO.builder()
                .name(name).build();
        final IdResponse response = webClient.post()
                .uri("http://localhost:" + port + "/products")
                .body(Mono.just(createProductTO), CreateProductTO.class)
                .retrieve()
                .bodyToMono(IdResponse.class).block();
        assert response != null;
        return response.getId();
    }
}

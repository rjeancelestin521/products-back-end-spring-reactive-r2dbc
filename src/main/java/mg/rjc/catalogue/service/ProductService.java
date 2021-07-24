package mg.rjc.catalogue.service;

import mg.rjc.catalogue.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> getAllProducts();
    Mono<Product> getProductById(final Long productId);
    Mono<Product> createProduct(final Product product);
    Mono<Product> updateProduct(final Long productId, final Mono<Product> productMono);
    Mono<Void> deleteProductById(final Long productId);
    Flux<Product> findProductAvailableIsTrue();
    Flux<Product> findProductSelectedIsTrue();
    Flux<Product> findProductsByContains(String name);
}

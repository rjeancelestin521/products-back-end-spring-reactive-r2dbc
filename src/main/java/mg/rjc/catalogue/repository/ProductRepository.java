package mg.rjc.catalogue.repository;

import mg.rjc.catalogue.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findProductByAvailableIsTrue();
    Flux<Product> findProductBySelectedIsTrue();
    Flux<Product> findProductByNameContains(String name);
}

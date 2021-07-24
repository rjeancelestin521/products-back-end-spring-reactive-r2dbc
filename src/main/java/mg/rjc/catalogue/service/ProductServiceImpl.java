package mg.rjc.catalogue.service;

import mg.rjc.catalogue.entity.Product;
import mg.rjc.catalogue.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<Product> getAllProducts() {
        return this.repository.findAll();
    }

    @Override
    public Mono<Product> getProductById(final Long productId) {
        return this.repository.findById(productId);
    }

    @Override
    public Mono<Product> createProduct(final Product product) {
        return repository.save(product);
    }

    @Override
    public Mono<Product> updateProduct(final Long productId, final Mono<Product> productMono) {
        return this.repository.findById(productId)
                .flatMap(product -> productMono.map(u -> {
                    product.setName(u.getName());
                    product.setPrice(u.getPrice());
                    product.setQuantity(u.getQuantity());
                    product.setAvailable(u.isAvailable());
                    product.setSelected(u.isSelected());
                    return product;
                })).flatMap(this.repository::save);
    }

    @Override
    public Mono<Void> deleteProductById(final Long productId) {
        return this.repository.deleteById(productId);
    }

    @Override
    public Flux<Product> findProductAvailableIsTrue() {
        return this.repository.findProductByAvailableIsTrue();
    }

    @Override
    public Flux<Product> findProductSelectedIsTrue() {
        return this.repository.findProductBySelectedIsTrue();
    }

    @Override
    public Flux<Product> findProductsByContains(String name) {
        return this.repository.findProductByNameContains(name);
    }
}

package mg.rjc.catalogue.controller;

import mg.rjc.catalogue.entity.Product;
import mg.rjc.catalogue.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = "/all")
    public Flux<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping(path = "/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Long productId) {
        return this.productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Product> createProduct(@RequestBody Mono<Product> productMono) {
        return productMono.flatMap(this.productService::createProduct);
    }

    @PutMapping(path = "/update/{productId}")
    public Mono<Product> updateProduct(@PathVariable Long productId, @RequestBody Mono<Product> productMono) {
        return this.productService.updateProduct(productId, productMono);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public Mono<Void> deleteProductById(@PathVariable Long productId) {
        return this.productService.deleteProductById(productId);
    }

    @GetMapping(path = "/availableProduct")
    public Flux<Product> productAvailable() {
        return this.productService.findProductAvailableIsTrue();
    }

    @GetMapping(path = "/selectedProduct")
    public Flux<Product> productSelected() {
        return this.productService.findProductSelectedIsTrue();
    }

    @GetMapping(path = "/searchByName")
    public Flux<Product> getProductsByNameContains(@RequestParam String name) {
        return this.productService.findProductsByContains(name);
    }


}

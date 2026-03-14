package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Product;
import com.sba301.backendpe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product create(Product product) {
        validate(product, null);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<String> getCategories() {
        return List.of("Electronics", "Books", "Fashion", "Home", "Sports");
    }

    private void validate(Product product, Long editingId) {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }
        if (product.getProductName().length() > 100) {
            throw new RuntimeException("Product name max length is 100");
        }
        if (productRepository.existsByProductName(product.getProductName())) {
            if (editingId == null || !productRepository.findById(editingId)
                    .map(p -> p.getProductName().equals(product.getProductName()))
                    .orElse(false)) {
                throw new RuntimeException("Duplicate product name");
            }
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new RuntimeException("Category is required");
        }
        if (product.getPrice() <= 0 || product.getPrice() > 99999) {
            throw new RuntimeException("Price must be > 0 and <= 99999");
        }
        if (product.getDescription() != null && product.getDescription().length() > 500) {
            throw new RuntimeException("Description max length is 500");
        }
    }
}

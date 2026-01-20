package org.juanlopezaranzazu.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import org.juanlopezaranzazu.entity.Product;
import org.juanlopezaranzazu.repository.ProductRepository;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.listAll();
    }

    public Product findById(Long id) {
        return productRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no existe"));
    }

    @Transactional
    public Product create(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product product = findById(id);
        product.name = updated.name;
        product.description = updated.description;
        product.price = updated.price;
        product.stock = updated.stock;
        return product;
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}

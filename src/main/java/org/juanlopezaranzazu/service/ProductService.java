package org.juanlopezaranzazu.service;

import org.juanlopezaranzazu.dto.ProductResponse;
import org.juanlopezaranzazu.dto.ProductRequest;
import org.juanlopezaranzazu.entity.Product;
import org.juanlopezaranzazu.exception.ResourceNotFoundException;
import org.juanlopezaranzazu.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        return productRepository.listAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con id " + id + " no fue encontrado"));
        return toResponse(product);
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        product.name = request.name;
        product.description = request.description;
        product.price = request.price;
        product.stock = request.stock;

        productRepository.persist(product);
        return toResponse(product);
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con id " + id + " no fue encontrado"));

        product.name = request.name;
        product.description = request.description;
        product.price = request.price;
        product.stock = request.stock;

        return toResponse(product);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.deleteById(id)) {
            throw new ResourceNotFoundException("El producto con id " + id + " no fue encontrado");
        }
    }

    private ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.id = product.id;
        response.name = product.name;
        response.description = product.description;
        response.price = product.price;
        response.stock = product.stock;
        return response;
    }
}

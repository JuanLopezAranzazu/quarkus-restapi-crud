package org.juanlopezaranzazu.repository;

import org.juanlopezaranzazu.entity.Product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}

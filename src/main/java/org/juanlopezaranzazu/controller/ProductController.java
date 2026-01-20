package org.juanlopezaranzazu.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.juanlopezaranzazu.entity.Product;
import org.juanlopezaranzazu.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    @Inject
    ProductService productService;

    @GET
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @POST
    @Transactional
    public Product create(Product product) {
        return productService.create(product);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product product) {
        return productService.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        productService.delete(id);
    }
}

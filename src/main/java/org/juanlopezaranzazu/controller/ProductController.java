package org.juanlopezaranzazu.controller;

import org.juanlopezaranzazu.dto.ProductRequest;
import org.juanlopezaranzazu.dto.ProductResponse;
import org.juanlopezaranzazu.entity.Product;
import org.juanlopezaranzazu.service.ProductService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    public List<ProductResponse> getAll() {
        return productService.findAll();
    }

    @GET
    @Path("/{id}")
    public ProductResponse getById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @POST
    public ProductResponse create(@Valid ProductRequest request) {
        return productService.create(request);
    }

    @PUT
    @Path("/{id}")
    public ProductResponse update(@PathParam("id") Long id,
                                  @Valid ProductRequest request) {
        return productService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        productService.delete(id);
    }
}

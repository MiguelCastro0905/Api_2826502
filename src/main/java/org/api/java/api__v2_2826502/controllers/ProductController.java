package org.api.java.api__v2_2826502.controllers;

import org.api.java.api__v2_2826502.entities.Product;
import org.api.java.api__v2_2826502.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    //inyectar el servicio
    @Autowired
    private ProductService productService;

    //endpoint
    //listar todos los productos
    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();

    }

    //End Point
    //obtener un producto por id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }


    //Endpoint
    //crear un producto

    @PostMapping("/products")
    public Product crearProducto
            (@RequestBody Product product) {
        return productService.save(product);
    }
    //Endpoint: Actualizar producto
    @PutMapping("/products/{id}")
    public Product actualizarProducto(
            @RequestBody Product product,
            @PathVariable Long id){
       return productService.cambiarProduct(product, id);

    }
   @DeleteMapping("/products/{id}")
   public Product borrarProducto(
           @PathVariable Long id){
        return productService.eliminarProducto( id);}



}


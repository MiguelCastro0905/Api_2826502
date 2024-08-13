package org.api.java.api__v2_2826502.controllers;


import jakarta.validation.Valid;
import org.api.java.api__v2_2826502.entities.Product;
import org.api.java.api__v2_2826502.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    //inyectar el servicio
    @Autowired
    private ProductService productService;

    //endpoint
    //listar todos los productos
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.findAll());

    }

    //End Point
    //obtener un producto por id
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        //Verificar si existe el producto por id
        Optional<Product> optProduct = productService.findById(id);
        if (optProduct.isPresent()) {
            //se encontro el producto
            return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
        } else {
            return ResponseEntity.badRequest().body("Product with id: " + id + " Not found");
        }


    }


    //Endpoint
    //crear un producto

    @PostMapping("/products")
    public ResponseEntity<?> crearProducto
            (@Valid @RequestBody Product product ,
             BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            //si existen errores de validacion
            //en los datos del body
            List<String> erroresvalidacion = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                erroresvalidacion.add(error.getDefaultMessage());
            }
            return ResponseEntity
                    .badRequest()
                    .body(erroresvalidacion);
        }
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productService.save(product));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest().body(
                            "ERROR creating product"
                    );
        }
    }

    //Endpoint: Actualizar producto
    @PutMapping("/products/{id}")
    public ResponseEntity<?> actualizarProducto(
            @RequestBody Product product,
            @PathVariable Long id) {

        Optional<Product> prodAct =
                productService.cambiarProduct(product, id);
        if (prodAct.isPresent()) {
            //si el producto a actulizar
            return ResponseEntity.ok()
                    .body(prodAct.get());

        } else {
            return ResponseEntity.badRequest()
                    .body("product with id : " + id + "not found");
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(
            @PathVariable Long id) {
        Optional<Product> proDlt =
                productService.eliminarProducto(id);
        if (proDlt.isPresent()) {
            return ResponseEntity.ok().body(proDlt.get());
        } else {
            return ResponseEntity.badRequest().body("product with id: " + id + " not delete");
        }

    }
}


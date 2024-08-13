package org.api.java.api__v2_2826502.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.api.java.api__v2_2826502.entities.Product;
import org.api.java.api__v2_2826502.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    //Dependencia inyectada
    //objeto necesario para cumplir
    //operaciones en una clase
    @Autowired
    private ProductRepository repositorio;

    //CRUD:
    //seleccionar todos los
    //productos:
    //SQL: select * from
    public List<Product> findAll() {
        return (List<Product>)
                repositorio.findAll();


    }
    //seleccionar producto por id
    //SQl : selec: from whre id=

    public Optional<Product> findById(Long id) {
        return repositorio.findById(id);

    }


    //insetar nuevo producto
    @Transactional
    public Product save(Product product) {
        //save:guardar nuevo producto
        //      en bd
        return repositorio.save(product);
    }


    //actualizar productos
    @Transactional
    public Optional<Product> cambiarProduct(
            Product product, long id) {
        //1.verificar si existe el producto por id
        Optional<Product> prodUpd = this.findById(id);
        if (prodUpd.isPresent()) {
            //2.Si existe el producto
            //obtener en una variable producto
            //el optional
            Product actualizacion = prodUpd.get();
            //3.Actulizar atributos del producto obtenido
            actualizacion.setName(product.getName());
            actualizacion.setDescription((product.getDescription()));
            actualizacion.setPrice(product.getPrice());
            //4.Grabar cambios
            repositorio.save(actualizacion);
            //5. convertir el producto en Optinal
            return Optional.of(actualizacion);
        } else {
            return prodUpd;
        }


    }

    @Transactional
    public Optional<Product> eliminarProducto(
            long id) {
        Optional<Product> proDlt = this.findById(id);

        if (proDlt.isPresent()) {
            Product eliminar = proDlt.get();
            eliminar.setId(id);
            repositorio.delete(eliminar);
            return Optional.of(eliminar);
        } else {
            return proDlt;
        }
    }
}

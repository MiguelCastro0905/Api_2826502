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
    public List<Product> findAll(){
        return (List<Product>)
                repositorio.findAll();


    }
    //seleccionar producto por id
    //SQl : selec: from whre id=

    public Product findById(Long id){
        return repositorio.findById(id).get();

    }


    //insetar nuevo producto
    @Transactional
    public Product save(Product product){
        //save:guardar nuevo producto
        //      en bd
        return repositorio.save(product);
    }


    //actualizar productos
    @Transactional
    public Product cambiarProduct(
            Product product, long id){
        //1.obtener el producto por id
        Product prodUpd = this.findById(id);
        //2. actualizar el estado del objeto
        // encontrado
        prodUpd.setDescription(product.getDescription());
        prodUpd.setPrice(product.getPrice());
        prodUpd.setName(product.getName());
        //3.grabar(guardar) cambiar bd
        return repositorio.save(prodUpd);
    }
    @Transactional
    public Product eliminarProducto(
             long id){
        Product proDlt = this.findById(id);
        repositorio.delete(proDlt);
        return proDlt;
    }


}


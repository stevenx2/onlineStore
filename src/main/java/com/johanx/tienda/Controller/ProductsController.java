package com.johanx.tienda.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {


    @GetMapping
    public String xd(){
        return "Hola mundo";
    }
}

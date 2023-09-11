package dev.umang.productcatalog.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {


      //@Autowired
      //Field Injection : not best practice
      private ProductService productService;

      
      //COnstructor Injection : Recommended Practice
      public ProductController(@Qualifier("fakeStoreProductServiceImpl")  ProductService productService){
            this.productService = productService;
      }

      //Setter Injection : Not so much recommended.
      public void setProductService(ProductService productService){
            this.productService = productService;
      }

      @GetMapping
      public void getAllProducts(){}

      @GetMapping("{id}")
      public GenericProductDTO getProductById( @PathVariable("id") Long id){

            return productService.getProductById(id);
            //return "Here is get Product by Id "+id;
      }

      @DeleteMapping("{id}")
      public void deleteProductById(){}

      @PostMapping
      public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
            return productService.createProduct(genericProductDTO);
      }

      @PutMapping("{id}")
      public void updateProductById(){}

       
      
}

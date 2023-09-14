package dev.umang.productcatalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;
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
      public List<GenericProductDTO> getAllProducts(){

            return productService.getAllProducts();
      }

      @GetMapping("{id}")
      public GenericProductDTO getProductById( @PathVariable("id") Long id) throws NotFoundException{

            return productService.getProductById(id);
            //return "Here is get Product by Id "+id;
      }

      @DeleteMapping("{id}")
      public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
            ResponseEntity<GenericProductDTO> responseEntity = 
            new ResponseEntity<GenericProductDTO>(productService.deleteProduct(id), HttpStatus.OK);
            return responseEntity;
      }

      @PostMapping
      public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
            return productService.createProduct(genericProductDTO);
      }

      @PutMapping("{id}")
      public ResponseEntity<GenericProductDTO> updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDTO genericProductDTO){
            
            return new ResponseEntity<GenericProductDTO>(productService.updateProduct(id, genericProductDTO), HttpStatus.OK);
      }

      @PatchMapping("{id}")
      public GenericProductDTO updatePatchProductById(@PathVariable("id") Long id, @RequestBody GenericProductDTO genericProductDTO){
            return productService.updatePatchProduct(id, genericProductDTO);
      }
       
      
}

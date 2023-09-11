package dev.umang.productcatalog.services;

import dev.umang.productcatalog.dtos.GenericProductDTO;


public interface ProductService {
      
      GenericProductDTO getProductById(Long id);

      GenericProductDTO createProduct(GenericProductDTO productDTO);

      

}

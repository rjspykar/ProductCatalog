package dev.umang.productcatalog.services;

import java.util.List;

import dev.umang.productcatalog.dtos.GenericProductDTO;


public interface ProductService {
      
      GenericProductDTO getProductById(Long id);

      GenericProductDTO createProduct(GenericProductDTO productDTO);

      GenericProductDTO deleteProduct(Long id);

      void updateProduct(Long id, GenericProductDTO productDTO);
      
      GenericProductDTO updatePatchProduct(Long id, GenericProductDTO productDTO);


      List<GenericProductDTO> getAllProducts();

      

}

package dev.umang.productcatalog.services;

import java.util.List;

import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;


public interface ProductService {
      
      GenericProductDTO getProductById(Long id) throws NotFoundException;

      GenericProductDTO createProduct(GenericProductDTO productDTO);

      GenericProductDTO deleteProduct(Long id);

      GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO);
      
      GenericProductDTO updatePatchProduct(Long id, GenericProductDTO productDTO);


      List<GenericProductDTO> getAllProducts();

      

}

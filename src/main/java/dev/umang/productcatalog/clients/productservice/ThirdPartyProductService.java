package dev.umang.productcatalog.clients.productservice;

import java.util.List;

import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;

public interface ThirdPartyProductService {

      GenericProductDTO getProductById(Long id) throws NotFoundException;

      GenericProductDTO createProduct(GenericProductDTO productDTO);

      GenericProductDTO deleteProduct(Long id);

      GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO);

      List<GenericProductDTO> getAllProducts();
}

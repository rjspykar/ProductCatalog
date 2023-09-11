package dev.umang.productcatalog.services;

import org.springframework.stereotype.Service;

import dev.umang.productcatalog.dtos.GenericProductDTO;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

      @Override
      public GenericProductDTO getProductById(Long id) {
            return new GenericProductDTO();
      }

      @Override
      public GenericProductDTO createProduct(GenericProductDTO productDTO) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
      }
      
}

package dev.umang.productcatalog.services;

import java.util.List;

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

      @Override
      public void updateProduct(Long id, GenericProductDTO productDTO) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
      }

      @Override
      public GenericProductDTO updatePatchProduct(Long id, GenericProductDTO productDTO) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updatePatchProduct'");
      }

      @Override
      public List<GenericProductDTO> getAllProducts() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
      }

      @Override
      public GenericProductDTO deleteProduct(Long id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
      }
      
}

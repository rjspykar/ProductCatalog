package dev.umang.productcatalog.services;

import java.util.List;

import org.springframework.stereotype.Service;
import dev.umang.productcatalog.clients.productservice.fakestore.FakeStoreProductServiceClient;
import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements ProductService {

      private FakeStoreProductServiceClient fakeStoreProductServiceClient;

      public FakeStoreProductServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient){
            this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
      }

      @Override
      public GenericProductDTO getProductById(Long id) throws NotFoundException {
            return fakeStoreProductServiceClient.getProductById(id);
      }

      @Override
      public GenericProductDTO createProduct(GenericProductDTO productDTO) {
            return fakeStoreProductServiceClient.createProduct(productDTO);
      }

      @Override
      public GenericProductDTO deleteProduct(Long id) {
            return fakeStoreProductServiceClient.deleteProduct(id);
      }

      @Override
      public GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO) {
            return fakeStoreProductServiceClient.updateProduct(id, productDTO);
      }

      @Override
      public GenericProductDTO updatePatchProduct(Long id, GenericProductDTO productDTO) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updatePatchProduct'");
      }

      @Override
      public List<GenericProductDTO> getAllProducts() {
            return fakeStoreProductServiceClient.getAllProducts();
      }

      

}

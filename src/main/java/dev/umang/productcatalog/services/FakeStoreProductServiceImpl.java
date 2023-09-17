package dev.umang.productcatalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import dev.umang.productcatalog.clients.productservice.fakestore.FakeStoreProductServiceClient;
import dev.umang.productcatalog.dtos.FakeStoreProductDTO;
import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements ProductService {

      private FakeStoreProductServiceClient fakeStoreProductServiceClient;

      public FakeStoreProductServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient){
            this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
      }

      private GenericProductDTO convertFakeStoreProductToGenericProduct(FakeStoreProductDTO fakeStoreProductDTO) {
            GenericProductDTO product = new GenericProductDTO();
            product.setId(fakeStoreProductDTO.getId());
            product.setImage(fakeStoreProductDTO.getImage());
            product.setTitle(fakeStoreProductDTO.getTitle());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setCategory(fakeStoreProductDTO.getCategory());
            product.setPrice(fakeStoreProductDTO.getPrice());
            return product;
      }

      private FakeStoreProductDTO convertGenericProductToFakeStoreProduct(GenericProductDTO genericProductDTO) {
            FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
            fakeStoreProductDTO.setId(genericProductDTO.getId());
            fakeStoreProductDTO.setImage(genericProductDTO.getImage());
            fakeStoreProductDTO.setTitle(genericProductDTO.getTitle());
            fakeStoreProductDTO.setDescription(genericProductDTO.getDescription());
            fakeStoreProductDTO.setCategory(genericProductDTO.getCategory());
            fakeStoreProductDTO.setPrice(genericProductDTO.getPrice());
            return fakeStoreProductDTO;
        }
        
      
      @Override
      public GenericProductDTO getProductById(Long id) throws NotFoundException {


            return  convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.getProductById(id));
      }

      @Override
      public GenericProductDTO createProduct(GenericProductDTO productDTO) {
            return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.createProduct(convertGenericProductToFakeStoreProduct(productDTO)));
      }

      @Override
      public GenericProductDTO deleteProduct(Long id) {
            return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.deleteProduct(id));
      }

      @Override
      public GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO) {
            return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.updateProduct(id, convertGenericProductToFakeStoreProduct(productDTO)));
      }

      @Override
      public GenericProductDTO updatePatchProduct(Long id, GenericProductDTO productDTO) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updatePatchProduct'");
      }

      @Override
      public List<GenericProductDTO> getAllProducts() {

            List<FakeStoreProductDTO> res =  fakeStoreProductServiceClient.getAllProducts();
            List<GenericProductDTO> ans =new ArrayList<>(); 
            res.forEach( e->{ans.add(convertFakeStoreProductToGenericProduct(e));} );
            return ans;
      }

      

}

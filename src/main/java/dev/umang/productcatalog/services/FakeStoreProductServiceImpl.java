package dev.umang.productcatalog.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.umang.productcatalog.dtos.FakeStoreProductDTO;
import dev.umang.productcatalog.dtos.GenericProductDTO;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements ProductService{


      private RestTemplateBuilder restTemplateBuilder;
      private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";

      private String createProductRequestURL = "https://fakestoreapi.com/products";

      public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
            this.restTemplateBuilder = restTemplateBuilder;
      }

      public GenericProductDTO createProduct(GenericProductDTO productDTO){
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<GenericProductDTO> responseEntity = 
                  restTemplate.postForEntity(createProductRequestURL, productDTO, GenericProductDTO.class);

            

            return responseEntity.getBody();
      }

      @Override
      public GenericProductDTO getProductById(Long id) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDTO> responseEntity = 
                  restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDTO.class, id);

            
                  FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

            
            GenericProductDTO product = new GenericProductDTO();
            product.setImage(fakeStoreProductDTO.getImage());
            product.setTitle(fakeStoreProductDTO.getTitle());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setCategory(fakeStoreProductDTO.getCategory());
            product.setPrice(fakeStoreProductDTO.getPrice());

            
            responseEntity.getStatusCode();

            return product;
      }
      
}

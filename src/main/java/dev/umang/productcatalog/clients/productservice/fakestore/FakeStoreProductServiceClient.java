package dev.umang.productcatalog.clients.productservice.fakestore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.umang.productcatalog.clients.productservice.ThirdPartyProductService;
import dev.umang.productcatalog.dtos.FakeStoreProductDTO;
import dev.umang.productcatalog.dtos.GenericProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;


/**
 * Wrapper over  fakestore API
 */

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductService {

      private RestTemplateBuilder restTemplateBuilder;
      private final String specificProductRequestURL = "https://fakestoreapi.com/products/{id}";

      private final String getAllProductsURL = "https://fakestoreapi.com/products";

      private String createProductRequestURL = "https://fakestoreapi.com/products";

      public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
            this.restTemplateBuilder = restTemplateBuilder;
      }

      public GenericProductDTO createProduct(GenericProductDTO productDTO) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<GenericProductDTO> responseEntity = restTemplate.postForEntity(createProductRequestURL,
                        productDTO, GenericProductDTO.class);

            return responseEntity.getBody();
      }

      @Override
      public GenericProductDTO getProductById(Long id) throws NotFoundException {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductRequestURL,
                        FakeStoreProductDTO.class, id);

            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

            if (fakeStoreProductDTO == null) {
                  throw new NotFoundException("Product with id " + id + " doesn't exist");
            }

            GenericProductDTO product = convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
            return product;
      }

      @Override
      public GenericProductDTO updateProduct(Long id, GenericProductDTO productDTO) {
            RestTemplate restTemplate = restTemplateBuilder.build();

            // restTemplate.put(specificProductRequestURL, productDTO, id);
            RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
            ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate
                        .responseEntityExtractor(FakeStoreProductDTO.class);
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(specificProductRequestURL,
                        HttpMethod.PUT, requestCallback, responseExtractor, id);

            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

            GenericProductDTO product = convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
            return product;

      }

      @Override
      public List<GenericProductDTO> getAllProducts() {

            RestTemplate restTemplate = restTemplateBuilder.build();

            ResponseEntity<FakeStoreProductDTO[]> resp = restTemplate.getForEntity(getAllProductsURL,
                        FakeStoreProductDTO[].class);

            List<GenericProductDTO> res = new ArrayList<>();

            for (FakeStoreProductDTO fakeStoreProductDTO : resp.getBody()) {
                  GenericProductDTO product = new GenericProductDTO();
                  product.setId(fakeStoreProductDTO.getId());
                  product.setImage(fakeStoreProductDTO.getImage());
                  product.setTitle(fakeStoreProductDTO.getTitle());
                  product.setDescription(fakeStoreProductDTO.getDescription());
                  product.setCategory(fakeStoreProductDTO.getCategory());
                  product.setPrice(fakeStoreProductDTO.getPrice());
                  res.add(product);
            }

            return res;
      }

      @Override
      public GenericProductDTO deleteProduct(Long id) {
            RestTemplate restTemplate = restTemplateBuilder.build();

            RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
            ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate
                        .responseEntityExtractor(FakeStoreProductDTO.class);
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(specificProductRequestURL,
                        HttpMethod.DELETE, requestCallback, responseExtractor, id);

            assert responseEntity != null;
            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
            assert fakeStoreProductDTO != null;
            return convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);

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

}
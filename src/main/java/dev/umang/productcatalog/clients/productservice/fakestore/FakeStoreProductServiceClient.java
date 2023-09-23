package dev.umang.productcatalog.clients.productservice.fakestore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.umang.productcatalog.clients.productservice.ThirdPartyProductService;
import dev.umang.productcatalog.dtos.FakeStoreProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;


/**
 * Wrapper over  fakestore API
 */

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductService {

      private RestTemplateBuilder restTemplateBuilder;

      @Value("${fakestore_api_url}")
      private String fakestore_api_url;

      @Value("${fakestore_api_url_path}")
      private String fakestore_api_url_path;

      private String specificProductRequestURL;

      private String getAllProductsURL;

      private String createProductRequestURL;

      public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
       @Value("${fakestore_api_url}") String fakestore_api_url,
       @Value("${fakestore_api_url_path}") String fakestore_api_url_path
      ) {
            this.restTemplateBuilder = restTemplateBuilder;
            specificProductRequestURL = fakestore_api_url + fakestore_api_url_path + "/{id}";
            getAllProductsURL = fakestore_api_url+fakestore_api_url_path;
            createProductRequestURL = getAllProductsURL;
      }

      public FakeStoreProductDTO createProduct(FakeStoreProductDTO productDTO) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(createProductRequestURL,
                        productDTO, FakeStoreProductDTO.class);

            return responseEntity.getBody();
      }

      @Override
      public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductRequestURL,
                        FakeStoreProductDTO.class, id);

            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

            if (fakeStoreProductDTO == null) {
                  throw new NotFoundException("Product with id " + id + " doesn't exist");
            }

            return fakeStoreProductDTO;
      }

      @Override
      public FakeStoreProductDTO updateProduct(Long id, FakeStoreProductDTO productDTO) {
            RestTemplate restTemplate = restTemplateBuilder.build();

            // restTemplate.put(specificProductRequestURL, productDTO, id);
            RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
            ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate
                        .responseEntityExtractor(FakeStoreProductDTO.class);
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(specificProductRequestURL,
                        HttpMethod.PUT, requestCallback, responseExtractor, id);

            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

            return fakeStoreProductDTO;

      }

      @Override
      public List<FakeStoreProductDTO> getAllProducts() {

            RestTemplate restTemplate = restTemplateBuilder.build();

            ResponseEntity<FakeStoreProductDTO[]> resp = restTemplate.getForEntity(getAllProductsURL,
                        FakeStoreProductDTO[].class);

            List<FakeStoreProductDTO> res = new ArrayList<>();

            for (FakeStoreProductDTO fakeStoreProductDTO : resp.getBody()) {
                  FakeStoreProductDTO product = new FakeStoreProductDTO();
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
      public FakeStoreProductDTO deleteProduct(Long id) {
            RestTemplate restTemplate = restTemplateBuilder.build();

            RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
            ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate
                        .responseEntityExtractor(FakeStoreProductDTO.class);
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(specificProductRequestURL,
                        HttpMethod.DELETE, requestCallback, responseExtractor, id);

            assert responseEntity != null;
            FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
            assert fakeStoreProductDTO != null;
            return fakeStoreProductDTO;

      }


}

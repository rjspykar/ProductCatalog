package dev.umang.productcatalog.clients.productservice;

import java.util.List;

import dev.umang.productcatalog.dtos.FakeStoreProductDTO;
import dev.umang.productcatalog.exceptions.NotFoundException;

public interface ThirdPartyProductService {

      FakeStoreProductDTO getProductById(Long id) throws NotFoundException;

      FakeStoreProductDTO createProduct(FakeStoreProductDTO productDTO);

      FakeStoreProductDTO deleteProduct(Long id);

      FakeStoreProductDTO updateProduct(Long id, FakeStoreProductDTO productDTO);

      List<FakeStoreProductDTO> getAllProducts();
}

package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRespositpry;

@Service
public class ProductService {
  private ProductRespositpry productRespositpry;
  public ProductService(ProductRespositpry productRespositpry) {
    this.productRespositpry = productRespositpry;
  }
  public Product saveProduct(Product product) {
    return this.productRespositpry.save(product);
  }
  public List<Product> getAllProducts() {
    return this.productRespositpry.findAll();
  }

  public Optional<Product> getProductById(long id) {
    return Optional.ofNullable(this.productRespositpry.findById(id));
  }
  public void deleteById(long id) {
    this.productRespositpry.deleteById(id);
  }
}

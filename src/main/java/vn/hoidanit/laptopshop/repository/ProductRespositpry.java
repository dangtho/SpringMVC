package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.Product;

public interface ProductRespositpry extends JpaRepository<Product, Long> {
  public Product save(Product product);
  public List<Product> findAll();
  public Product findById(long id);
  public void deleteById(long id);
}

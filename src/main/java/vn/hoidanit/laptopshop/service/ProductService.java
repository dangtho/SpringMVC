package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRespositpry;

@Service
public class ProductService {
  private ProductRespositpry productRespositpry;
  private CartRepository  cartRepository;
  private CartDetailRepository cartDetailRepository;
  private UserService userService;
  public ProductService(ProductRespositpry productRespositpry, CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserService userService) {
    this.productRespositpry = productRespositpry;
    this.cartRepository = cartRepository;
    this.cartDetailRepository = cartDetailRepository;
    this.userService = userService;
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

  public Cart findByUser(User user) {
    return this.cartRepository.findCartByUser(user);
  }
  
  public void deleteCart(Long cartId) {
    this.cartRepository.deleteById(cartId);
  }
  
  public void saveCart(Cart cart) {
    this.cartRepository.save(cart);
  }
  public void handleAddProductToCart(String userEmail, long productId, HttpSession session) {
    User user = this.userService.getUserByEmail(userEmail);
    if (user != null) {
      // Implementation for adding product to cart
      Cart cart = findByUser(user);
      if (cart == null) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setSum(0L);
        cart = newCart;
      }
      // update cart
          Long sum = cart.getSum() + 1;
          cart.setSum(sum);
          this.cartRepository.save(cart);
      Optional<Product> product = Optional.ofNullable(this.productRespositpry.findById(productId));
      if (product.isPresent()) {
        Product realProduct = product.get();
        CartDetail existingCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
        // Implementation for adding product to cart
        CartDetail cartDetail = new CartDetail();
        if (existingCartDetail != null) {
          cartDetail = existingCartDetail;
          cartDetail.setQuantity(cartDetail.getQuantity() + 1);
          // update cart
          sum = sum - 1;
          cart.setSum(sum);
          this.cartRepository.save(cart);
        } else {
          cartDetail.setCart(cart);
          cartDetail.setProduct(realProduct);
          cartDetail.setQuantity(1);
          cartDetail.setPrice(realProduct.getPrice());
        }
          session.setAttribute("sum", sum);
          this.cartDetailRepository.save(cartDetail);
      }
    }
  }
  
  public void handleRemoveProductFromCart(Long cartDetailId, HttpServletRequest request) {  
    this.cartDetailRepository.deleteById(cartDetailId);
    // delete cart
    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    User user = new User();
    user.setId(userId);
    Cart cart = findByUser(user);
    if (cart != null) {
      Long sum = cart.getSum() - 1;
      if (sum < 1) {
        deleteCart(cart.getCartId());
        session.setAttribute("sum", 0);
      } else {
        cart.setSum(sum);
        saveCart(cart);
        session.setAttribute("sum", sum);
      }
    }
  }
  public void handleBeforeCheckout(List<CartDetail> cartDetailList) {
    for (CartDetail cartDetail : cartDetailList) {
      CartDetail existingCartDetail = this.cartDetailRepository.findById(cartDetail.getCartDetailId()).orElse(null);
      if (existingCartDetail != null) {
        existingCartDetail.setQuantity(cartDetail.getQuantity());
        this.cartDetailRepository.save(existingCartDetail);
      }
    }
  }
}

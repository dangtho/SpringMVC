package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_etail")
public class CartDetail {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long cartDetailId;
  private int quantity;
  private double price;
  @ManyToOne
  @JoinColumn(name = "cartId")
  private Cart cart;
  @ManyToOne
  @JoinColumn(name = "productId")
  private Product product;
  
  @Override
  public String toString() {
    return "CartDetail [cartDetailId=" + cartDetailId + ", quantity=" + quantity + ", price=" + price + ", cart=" + cart
        + ", product=" + product + "]";
  }
  public Long getCartDetailId() {
    return cartDetailId;
  }
  public void setCartDetailId(Long cartDetailId) {
    this.cartDetailId = cartDetailId;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public Cart getCart() {
    return cart;
  }
  public void setCart(Cart cart) {
    this.cart = cart;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  
}

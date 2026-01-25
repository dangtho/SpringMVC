package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long cartId;
  @OneToOne
  @JoinColumn(name = "userId")
  private User user;
  @OneToMany(mappedBy = "cart")
  private List<CartDetail> cartDetails;
  @Min(value = 0)
  private Long sum;
  
  @Override
  public String toString() {
    return "Cart [cartId=" + cartId + ", user=" + user + ", cartDetails=" + cartDetails + ", sum=" + sum + "]";
  }
  public Long getCartId() {
    return cartId;
  }
  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public List<CartDetail> getCartDetails() {
    return cartDetails;
  }
  public void setCartDetails(List<CartDetail> cartDetails) {
    this.cartDetails = cartDetails;
  }
  public Long getSum() {
    return sum;
  }
  public void setSum(Long sum) {
    this.sum = sum;
  }

}

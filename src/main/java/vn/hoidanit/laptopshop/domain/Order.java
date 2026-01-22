package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private long id = 0;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private double totalPrice;
  @OneToMany(mappedBy = "order")
  private List <OrderDetail> orderDetails;

  @Override
  public String toString() {
    return "Order [id=" + id + ", userId=" + user.getId() + ", totalPrice=" + totalPrice + "]";
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public double getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }
  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }
}

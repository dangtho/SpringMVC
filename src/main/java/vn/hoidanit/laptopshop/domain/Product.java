package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="products")
public class Product {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private long id = 0;
  @NotNull
  @NotEmpty(message = "Name is required")
  private String name;
  @DecimalMin(value = "0", inclusive = false, message = "Price must be greater than 0")
  private double price;
  private String image;
  @NotNull
  @NotEmpty(message = "Short description is required")
  @Column(columnDefinition = "MediumText")
  private String short_desc;
  @NotEmpty(message = "Detail description is required")
  @Column(columnDefinition = "MediumText")
  private String detail_desc;
  @Min(value = 1, message = "Quantity must be greater than 0")
  private int quantity;
  private String sold = "0";
  private String factory;
  private String target;

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", shortDescription="
        + short_desc + ", detailDescription=" + detail_desc + ", quantity=" + quantity + ", sold=" + sold
        + ", factory=" + factory + ", target=" + target + "]";
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public String getShortDescription() {
    return short_desc;
  }
  public void setShortDescription(String shortDescription) {
    this.short_desc = shortDescription;
  }
  public String getDetailDescription() {
    return detail_desc;
  }
  public void setDetailDescription(String detailDescription) {
    this.detail_desc = detailDescription;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getSold() {
    return sold;
  }
  public void setSold(String sold) {
    this.sold = sold;
  }
  public String getFactory() {
    return factory;
  }
  public void setFactory(String factory) {
    this.factory = factory;
  }
  public String getTarget() {
    return target;
  }
  public void setTarget(String target) {
    this.target = target;
  }
  
}

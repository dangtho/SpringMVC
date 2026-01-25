package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;





@Controller
public class ItemController {
  private final ProductService productService;
  public ItemController(ProductService productService) {
    this.productService = productService;
  }
  @GetMapping("/product/{id}")
  public String getDetailPage(Model model, @PathVariable long id) {
    model.addAttribute("product", productService.getProductById(id).get());
    return "client/product/detail";
  }
  @PostMapping("/add-product-to-cart/{id}")
  public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    System.out.println(">>>>>> Add product to cart: " + id);
      Long productId = id;
      String email = (String) request.getSession().getAttribute("email");
      this.productService.handleAddProductToCart(email, productId, session);
      return "redirect:/home";
  }
  
  @GetMapping("/cart-detail")
  public String getCartDetailPage(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    User user = new User();
    Long id = (Long) session.getAttribute("id");
    user.setId(id);
    Cart cart = this.productService.findByUser(user);
    List<CartDetail> cartDetailList = cart == null ? List.of() : cart.getCartDetails();
    double totalPrice = 0;
    for (CartDetail cartDetail : cartDetailList) {
      totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
    }
    model.addAttribute("cartDetails", cartDetailList);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("cart", cart);
    return "client/cart/show";
  }
  
  @PostMapping("/cart/remove/{id}")
  public String removeProductFromCart(@PathVariable long id, HttpServletRequest request) {
    System.out.println(">>>>>> Remove product from cart: " + id);
    this.productService.handleRemoveProductFromCart(id, request);
    return "redirect:/cart-detail";
  }
  // check oout
  @PostMapping("/cart/checkout")
  public String handleBeforeCheckout(@ModelAttribute("cart") Cart cart) {
    List<CartDetail> cartDetailList = cart == null ? List.of() : cart.getCartDetails();
    this.productService.handleBeforeCheckout(cartDetailList);
      return "redirect:/cart/checkout";
  }
  @GetMapping("/cart/checkout")
  public String checkout(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    User user = new User();
    Long id = (Long) session.getAttribute("id");
    user.setId(id);
    Cart cart = this.productService.findByUser(user);
    List<CartDetail> cartDetailList = cart == null ? List.of() : cart.getCartDetails();
    double totalPrice = 0;
    for (CartDetail cartDetail : cartDetailList) {
      totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
    }
    model.addAttribute("cartDetails", cartDetailList);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("cart", cart);
      return "client/cart/checkout";
  }
  @PostMapping("/place-order")
  public String handlePlaceOrder(HttpServletRequest request,
    @RequestParam("receiverName") String receiverName,
    @RequestParam("receiverAddress") String receiverAddress,
    @RequestParam("receiverPhone") String receiverPhone
  ) {
      HttpSession session = request.getSession(false);
      Long userId = (Long) session.getAttribute("id");
      // this.productService.handlePlaceOrder(userId, receiverName, receiverAddress, receiverPhone);
      return "redirect:/home";
  }
}

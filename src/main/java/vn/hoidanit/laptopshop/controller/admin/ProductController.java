package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;


@Controller
public class ProductController {
  private ProductService productService;
  private UploadService uploadService;

  public ProductController(
    ProductService productService,
    UploadService uploadService
  ) {
    this.productService = productService;
    this.uploadService = uploadService;
  }

@GetMapping("/admin/product")
  public String getProductPage(Model model) {
    List<Product> list = this.productService.getAllProducts();
    System.out.println("Products list: " + list);
    model.addAttribute("productsList", list);
    return "admin/product/product";
  }
  // create product
@GetMapping("/admin/product/create")
  public String createProduct(Model model) {
    model.addAttribute("newProduct", new Product());
    return "admin/product/create";
  }
  @PostMapping("/admin/product/create")
  public String createProduct(Model model,
    @ModelAttribute("newProduct") @Valid Product product,
    BindingResult newProductBindingResult,
    @RequestParam("imageFile") MultipartFile imageFile
  ) {
    // validate
      List<FieldError> errors = newProductBindingResult.getFieldErrors();
    for (FieldError error : errors ) {
        System.out.println (">>>>>>>>>>>>>> "+error.getField() + " - " + error.getDefaultMessage());
    }
    if (newProductBindingResult.hasErrors()) {
      return "admin/product/create";
    }
    product.setImage(this.uploadService.handleUploadFile(imageFile, "product"));
    model.addAttribute("newProduct", new Product());
    this.productService.saveProduct(product);
    return "redirect:/admin/product";
  }
  // view detail
  @GetMapping("/admin/product/{idProduct}")
  public String getProductDetailPage(Model model, @PathVariable("idProduct") long id) {
    model.addAttribute("product", this.productService.getProductById(id).get());
    return "admin/product/detail";
  }

  // delete
  @GetMapping("/admin/product/delete/{idProduct}")
  public String deleteProduct(Model model, @PathVariable("idProduct") long id) {
    model.addAttribute("product", this.productService.getProductById(id).get());
    return "admin/product/delete";
  }

  @PostMapping("/admin/product/delete")
  public String deleteProduct(@ModelAttribute Product product) {
    this.productService.deleteById(product.getId());
    return "redirect:/admin/product";
  }
  // update
  @GetMapping("/admin/product/update/{idProduct}")
  public String updateProduct(Model model, @PathVariable("idProduct") long id) {
    model.addAttribute("product", this.productService.getProductById(id).get());
    return "admin/product/update";
  }
  @PostMapping("/admin/product/update")
  public String updateProduct(Model model,
    @ModelAttribute("product") @Valid Product product,
    BindingResult productBindingResult,
    @RequestParam("imageFile") MultipartFile imageFile
  ) {
    Product existingProduct = this.productService.getProductById(product.getId()).get();
    // validate
    if (productBindingResult.hasErrors()) {
      product.setImage(existingProduct.getImage());
      model.addAttribute("product", product);
      return "admin/product/update";
    }
    if (existingProduct.getId() != 0) {
      String image = this.uploadService.handleUploadFile(imageFile, "product");
    if (!imageFile.isEmpty()) {
    existingProduct.setImage(image);
    }
    existingProduct.setName(product.getName());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setDetailDescription(product.getDetailDescription());
    existingProduct.setShortDescription(product.getShortDescription());
    existingProduct.setFactory(product.getFactory());
    existingProduct.setTarget(product.getTarget());
    existingProduct.setQuantity(product.getQuantity());
    existingProduct.setSold(product.getSold());
    }
    model.addAttribute("product", existingProduct);
    this.productService.saveProduct(existingProduct);
    return "redirect:/admin/product";
  }
}

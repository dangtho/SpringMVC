package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;



@Controller
public class UserController {
private final UserService userService;
private final UploadService uploadService;
private final PasswordEncoder passwordEncoder;

  public UserController(UserService userService, 
    UploadService uploadService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.uploadService = uploadService;
    this.passwordEncoder = passwordEncoder;
  }
  @RequestMapping("/")
  public String getHomePage(Model model) {
    String test = this.userService.handleHello();
    List <User> users = this.userService.getAllUserByEmail("hoidanit@gmail.com");
    System.out.println("Users: " + users);
    model.addAttribute("eric", test);
    return "hello";
  }

  @RequestMapping("/admin/user/create")
  public String getCreateUserPage(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/create";
  }
  @RequestMapping("/admin/user")
  public String getUsers(Model model) {
    List<User> users = this.userService.getAll();
    System.out.println("Users list: " + users);
    model.addAttribute("usersList", users);
    return "admin/user/show";
  }

  @RequestMapping("/admin/user/{idUser}")
  public String getUsersDetailPage(@PathVariable long idUser, Model model) {
    User user = this.userService.getUserById(idUser);
    System.out.println("User information: " + user.toString());
    model.addAttribute("user", user);
    return "admin/user/detail";
  }

  @PostMapping("/admin/user/update")
  public String postUpdateUser( Model model,
    @ModelAttribute("user") User user,
    @RequestParam("avatarFile") MultipartFile avatarFile ) {
    User existingUser = this.userService.getUserById(user.getId());
    if (user.getId() != 0) {
      existingUser.setFullName(user.getFullName());
      existingUser.setAddress(user.getAddress());
      existingUser.setPhone(user.getPhone());
      existingUser.setRole(this.userService.getRole(user.getRole().getName()));
      existingUser.setAvatar(this.uploadService.handleUploadFile(avatarFile, "avartar"));
      this.userService.handleSaveUser(existingUser);
    }
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/update/{idUser}")
  public String getUsersUpdatePage(@PathVariable long idUser, Model model) {
    User user = this.userService.getUserById(idUser);
    System.out.println("User information: " + user.toString());
    model.addAttribute("user", user);
    return "admin/user/update-user";
  }

  @GetMapping("/admin/user/delete/{idUser}")
  public String getUsersDeletePage(@PathVariable long idUser, Model model) {
    User user = this.userService.getUserById(idUser);
    model.addAttribute("user", user);
    return "admin/user/delete-user";
  }
  @PostMapping("/admin/user/delete")
  public String postUsersDelete( Model model, @ModelAttribute("user") User user) {
    this.userService.deleteById(user.getId());
    System.out.println("Deleted user id: " + user.getId());
    return "redirect:/admin/user";
  }

  @PostMapping("/admin/user/create")
  public String createUserPage(Model model,
     @ModelAttribute("newUser") @Valid User user,
    BindingResult newUserBindingResult,
     @RequestParam("avatarFile") MultipartFile avatarFile) {
      // validate
      List<FieldError> errors = newUserBindingResult.getFieldErrors();
    for (FieldError error : errors ) {
        System.out.println (">>>>>>>>>>>>>> "+error.getField() + " - " + error.getDefaultMessage());
    }
    if (newUserBindingResult.hasErrors()) {
      return "admin/user/create";
    }
      String hasPassString = this.passwordEncoder.encode(user.getPassword());
      user.setAvatar(this.uploadService.handleUploadFile(avatarFile, "avartar"));
      user.setPassword(hasPassString);
      user.setRole(this.userService.getRole(user.getRole().getName()));
      this.userService.handleSaveUser(user);
    return "redirect:/admin/user";
  }
}
// @RestController
// public class UserController {
//   private UserService userService;
  
//   public UserController(UserService userService) {
//     this.userService = userService;
//   }
//   @GetMapping()
//   public String getHomePage() {
//     return this.userService.handleHello();
//   }
// }

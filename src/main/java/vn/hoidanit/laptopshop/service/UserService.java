package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDto;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }
  public String handleHello() {
    return "hello service";
  }
  public List<User> getAll() {
    return this.userRepository.findAll();
  }
  public List<User> getAllUserByEmail(String email) {
    return this.userRepository.findOneByEmail(email);
  }
  public void handleSaveUser(User user) {
    this.userRepository.save(user);
  }

  public User getUserById(long id) {
    return this.userRepository.findOneUserById(id);
  }
  public void deleteById(long id) {
    this.userRepository.deleteById(id);
  }

  public Role getRole(String nameRole) {
    return this.roleRepository.findRoleByName(nameRole);
  }
// register user
  public User registerDtoUser(RegisterDto registerDto) {
    User user = new User();
    user.setEmail(registerDto.getEmail());
    user.setPassword(registerDto.getPassword());
    user.setFullName(registerDto.getFirstName() + " " + registerDto.getLastName());
    return user;
  }

  // check email exists
  public boolean checkEmailExists(String email) {
    return this.userRepository.existsByEmail(email);
  }
  public User getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }
}

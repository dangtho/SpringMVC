package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User save(User user);
  List <User> findOneByEmail(String email);
  List<User> findAll();
  User findOneUserById(long id);
  void deleteById(long id);
  boolean existsByEmail(String email);
  User findByEmail(String email);
    List<User> findAllByOrderByFullNameAsc();
  List<User> findAllByOrderByFullNameDesc();
}

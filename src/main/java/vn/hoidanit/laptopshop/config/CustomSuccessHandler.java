package vn.hoidanit.laptopshop.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new org.springframework.security.web.DefaultRedirectStrategy();

  @Autowired
  private UserService userService;
  protected String determineTargetUrl(Authentication authentication) {
    // String role = authentication.getAuthorities().toString();
    // if (role.contains("ADMIN")) {
    //   return "/admin";
    // }
    // return "/home";
    Map <String, String> roleTargetUrl = Map.of(
      "ROLE_ADMIN", "/admin",
      "ROLE_USER", "/home"
    );
    final Collection<?> authorities = authentication.getAuthorities();
    for (var entry : authorities) {
      String authorityName = entry.toString();
      if (roleTargetUrl.containsKey(authorityName)) {
        return roleTargetUrl.get(authorityName).toString();
      }
    }
    throw new IllegalStateException();
  }
  private void clearAuthenticationAttributes(HttpServletRequest request, 
    Authentication authentication) {
    var session = request.getSession(false);
    if (session == null) {
      return;
    }
    // session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    // get email
    String email = authentication.getName();
    // get user 
    User user = this.userService.getUserByEmail(email);
    if (user != null) {
    session.setAttribute("fullName", user.getFullName());
    session.setAttribute("avatar", user.getAvatar());
    session.setAttribute("id", user.getId());
    session.setAttribute("email", user.getEmail());
    session.setAttribute("sum", user.getCart() == null ? 0 : user.getCart().getSum());
    }
  }
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
      return;
    }
    redirectStrategy.sendRedirect(request, response, targetUrl);
    clearAuthenticationAttributes(request, authentication);
  }
}

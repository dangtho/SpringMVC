package vn.hoidanit.laptopshop.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandle implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new org.springframework.security.web.DefaultRedirectStrategy();

  protected String determineTargetUrl(Authentication authentication) {
    // String role = authentication.getAuthorities().toString();
    // if (role.contains("ADMIN")) {
    //   return "/admin";
    // }
    // return "/home";
    Map <String, Object> roleTargetUrl = Map.of(
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
  private void clearAuthenticationAttributes(HttpServletRequest request) {
    var session = request.getSession();
    if (session == null) {
      return;
    }
    // session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
      return;
    }
    redirectStrategy.sendRedirect(request, response, targetUrl);
    clearAuthenticationAttributes(request);
  }

}

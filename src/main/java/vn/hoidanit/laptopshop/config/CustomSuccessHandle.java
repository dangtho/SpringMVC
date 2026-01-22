package vn.hoidanit.laptopshop.config;

import java.io.IOException;

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
    String role = authentication.getAuthorities().toString();
    if (role.contains("ADMIN")) {
      return "/admin";
    }
    return "/home";
  }
  private void clearAuthenticationAttributes(HttpServletRequest request) {
    var session = request.getSession(false);
    if (session == null) {
      return;
    }
    // session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    // TODO Auto-generated method stub
    String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
      return;
    }
    redirectStrategy.sendRedirect(request, response, targetUrl);
    clearAuthenticationAttributes(request);
  }

}

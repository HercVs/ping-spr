package gr.eduping.eduping.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Defines response content when a not authenticated client tries to access an endpoint.
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    )
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String json = "{\"code\": \"userNotAuthenticated\", \"message\": \"User must authenticate in order to access this endpoint\"}";

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

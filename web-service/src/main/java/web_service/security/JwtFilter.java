package web_service.security;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 00:45
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import web_service.service.impl.AccountDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * OncePerRequestFilter là Filter thực thi một lần duy nhất cho mỗi Request tới API.
 * Nó cung cấp một phương thức doFilterInternal() - trong phương thức này
 * ta sẽ triển khai: parse và validate chuỗi JWT, lấy thông tin người dùng (sử dụng UserDetailsService),
 * kiểm tra Authorization.
*/
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AccountDetailsServiceImpl accountDetailsService;

    // Chạy đầu tiên trước @PostMapping("/login")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            System.out.println("token: " + token);
            if (token != null && jwtUtility.validateJwtToken(token)) {
                UserDetails userDetails = accountDetailsService.loadUserByUsername(token);
                System.out.println("akdfbkajs");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /*
                * Sử dụng phương thức setAuthentication(authentication) set thông tin UserDetails
                * vào SecurityContext. Kể từ sau đó, chúng ta có thể lấy thông tin UserDetails bất cứ khi nào thông qua SecurityContext như sau:
                */
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        System.out.println("doFilterInternal() at JwtFilter in security package.");
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }
}

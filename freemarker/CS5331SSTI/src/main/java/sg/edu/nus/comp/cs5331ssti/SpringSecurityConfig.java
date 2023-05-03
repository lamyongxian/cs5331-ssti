package sg.edu.nus.comp.cs5331ssti;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import sg.edu.nus.comp.cs5331ssti.util.MultiReadHttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().xssProtection().disable();

        // Disable CSRF for forms (Test only)
        http.csrf().disable();
        http.cors().disable();

        // Allow non-authenticated on all endpoints (Test only)
        http.authorizeRequests().antMatchers("/**").permitAll();

        // Activate SSTI Sanitization
        boolean springSecurityFilterEnabled = Boolean.parseBoolean(System.getProperty("spring.security"));
        if (springSecurityFilterEnabled) {
            http.addFilterBefore(new OncePerRequestFilter() {
                @Override
                protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                    //ContentCachingRequestWrapper cachedRequest = new ContentCachingRequestWrapper(request);
                    MultiReadHttpServletRequest cachedRequest = new MultiReadHttpServletRequest(request);

                    // Get query string
                    //String queryString = ((HttpServletRequest) servletRequest).getQueryString();

                    String requestBody = "";
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        requestBody = IOUtils.toString(cachedRequest.getInputStream(), StandardCharsets.UTF_8);
                        requestBody = URLDecoder.decode(requestBody, StandardCharsets.UTF_8);
                    }

                    if (requestBody != null) {
                        Pattern regexCmd = Pattern.compile(".*?(\\$\\s*\\{.*(ls|cd|pwd|cat|echo|cp|mv|rm|mkdir|rmdir|touch|chmod|chown|chgrp|grep|sed|awk|cut|sort|uniq|tee|find|ssh|scp|tar|gzip|gunzip|curl|wget|ping|traceroute|nc|telnet|nmap|whoami|su|sudo|passwd).*\\}).*");
                        Pattern regexClassLoader = Pattern.compile(".*?(<#.*(freemarker.template.utility.Execute).*?>).*");

                        if (regexCmd.matcher(requestBody).find()) {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Command Injection Blocked!");
                            return;
                        } else if (regexClassLoader.matcher(requestBody).find()) {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: ClassLoader Injection Blocked!");
                            return;
                        }
                    }

                    filterChain.doFilter(cachedRequest, response);
                }

            }, UsernamePasswordAuthenticationFilter.class);
        }
        return http.build();
    }
}

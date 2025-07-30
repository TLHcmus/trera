package com.tule.trera.service.impl;

import com.tule.trera.model.User;
import com.tule.trera.model.types.ERole;
import com.tule.trera.repository.UserRepository;
import com.tule.trera.service.AuthService;
import com.tule.trera.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @Override
    public UserDetails validateToken(String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        return userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
    }

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                password
        ));

        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String login(String email, String password) {
        UserDetails userDetails = authenticate(email, password);

        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public String register(String email, String password) {
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(ERole.ROLE_USER)
                .build();

        // Save the user to the database
        userRepository.save(user);

        // Generate a JWT token for the newly registered user
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtUtil.generateToken(userDetails);
    }
}

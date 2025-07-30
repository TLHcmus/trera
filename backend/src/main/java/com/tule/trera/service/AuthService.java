package com.tule.trera.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails validateToken(String token);

    UserDetails authenticate(String email, String password);

    String login(String email, String password);

    String register(String email, String password);
}

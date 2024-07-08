package com.enigma.kelompok.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.kelompok.model.Role;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.UserRepository;
import com.enigma.kelompok.security.JwtTokenProvider;
import com.enigma.kelompok.utils.dto.UserDTO;
import com.enigma.kelompok.utils.response.Res;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;

  public AuthController(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtTokenProvider tokenProvider) {

    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody UserDTO request) {
    if (userRepository.findByUsername(request.getUsername()) != null) {
      return Res.renderJson(null, "username is taken", HttpStatus.BAD_REQUEST);
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    if (user.getRole() == null) {
      user.setRole(Role.ROLE_USER);
    }

    return Res.renderJson(
        user,
        "Successfully registered",
        HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody UserDTO request) {
    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(auth);

    String jwt = tokenProvider.generateToken(auth);
    Map<String, String> response = new HashMap<>();
    response.put("token", jwt);

    return Res.renderJson(
        response,
        "Login success",
        HttpStatus.OK);
  }
}

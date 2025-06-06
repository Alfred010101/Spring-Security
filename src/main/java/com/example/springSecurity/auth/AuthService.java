package com.example.springSecurity.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springSecurity.model.User;
import com.example.springSecurity.jwt.JwtService;
import com.example.springSecurity.model.Address;
import com.example.springSecurity.model.Role;
import com.example.springSecurity.repository.AddressRepository;
import com.example.springSecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService 
{

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) 
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails person = userRepository.findByUsername(request.getUsername()).orElseThrow();
   
        return AuthResponse.builder()
            .token(jwtService.getToken(person))
            .build();
    }

    public AuthResponse register(RegisterRequest request) 
    {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        Address address = Address.builder()
            .streetName(request.getAddress().getStreetName())
            .exteriorNumber(request.getAddress().getExteriorNumber())
            .interiorNumber(request.getAddress().getInteriorNumber())
            .postalCode(request.getAddress().getPostalCode())
            .references(request.getAddress().getReferences())
            .build();
        
        Address addressSave = addressRepository.save(address);

        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .addressId(addressSave.getId())
            .phone(request.getPhone())
            .role(Role.CUSTOMER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
}

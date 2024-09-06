package com.compasso.ecommerce_app.core.interfaces.services.controller;
import com.compasso.ecommerce_app.app.dto.users.UsersDTO;
import com.compasso.ecommerce_app.app.service.UsersService;
import com.compasso.ecommerce_app.core.security.JwtUtil;
import com.compasso.ecommerce_app.core.security.users.UsersAuthenticationRequest;
import com.compasso.ecommerce_app.core.security.users.UsersAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersDetailsService usersDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody @Valid UsersDTO usersDTO) {
        return ResponseEntity.ok(usersService.save(usersDTO));
    }

    @GetMapping("/get")
    public ResponseEntity<UsersDTO> get(@RequestParam String username) {
        return ResponseEntity.ok(usersService.getByName(username));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAutentication(@RequestBody UsersAuthenticationRequest user) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch(Exception e){
            throw new Exception("Senha incorreta", e);
        }

        UserDetails userDetails = usersDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(usersDetails);

        return ResponseEntity.ok(new UsersAuthenticationResponse(token));
    }

}

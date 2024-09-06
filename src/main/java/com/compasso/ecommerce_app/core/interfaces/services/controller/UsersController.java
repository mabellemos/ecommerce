package com.compasso.ecommerce_app.core.interfaces.services.controller;
import com.compasso.ecommerce_app.app.dto.users.UsersDTO;
import com.compasso.ecommerce_app.app.service.UsersService;
import com.compasso.ecommerce_app.core.security.JwtUtils;
import com.compasso.ecommerce_app.core.security.users.UsersAutheticationRequest;
import com.compasso.ecommerce_app.core.security.users.UsersAuthenticationResponse;
import com.compasso.ecommerce_app.core.security.users.UsersDetailsServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersDetailsServ usersDetailsServ;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody UsersDTO usersDTO) {
        return ResponseEntity.ok(usersService.save(usersDTO));
    }

    @GetMapping("/get")
    public ResponseEntity<UsersDTO> get(@RequestParam String username) {
        return ResponseEntity.ok(usersService.getByName(username));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAutentication(@RequestBody UsersAutheticationRequest user) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch(Exception e){
            throw new Exception("Senha incorreta", e);
        }

        UserDetails userDetails = usersDetailsServ.loadUserByUsername(user.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new UsersAuthenticationResponse(token));
    }

}

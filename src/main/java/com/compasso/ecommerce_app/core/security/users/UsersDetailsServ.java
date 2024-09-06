package com.compasso.ecommerce_app.core.security.users;

import com.compasso.ecommerce_app.core.interfaces.repository.UsersRepository;
import com.compasso.ecommerce_app.core.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersDetailsServ implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.login(username);

        if(users.isPresent()) {
            Users u = users.get();
            return new User(u.getUsername(), u.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Usuario incorreto");
    }

}
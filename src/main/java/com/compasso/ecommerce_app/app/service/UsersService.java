package com.compasso.ecommerce_app.app.service;

import com.compasso.ecommerce_app.app.dto.users.UsersDTO;
import com.compasso.ecommerce_app.core.interfaces.repository.UsersRepository;
import com.compasso.ecommerce_app.core.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    private UsersDTO mapToDTO(Users users, UsersDTO udto) {
        udto.setId(users.getId());
        udto.setUsername(users.getUsername());
        udto.setPassword(users.getPassword());

        return udto;
    }

    private Users mapToModel(Users users, UsersDTO udto) {
        users.setUsername(udto.getUsername());
        users.setPassword(encoder.encode(udto.getPassword()));

        return users;
    }

    public UsersDTO getById(Integer id) {
        return usersRepository.findById(id)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UsersDTO getByName(String username) {
        return usersRepository.findAll()
                .stream()
                .filter(users ->  users.getUsername().equals(username))
                .map(users -> mapToDTO(users, new UsersDTO()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer save(UsersDTO usersDTO) {
        Users user = new Users();
        mapToModel(user, usersDTO);
        usersRepository.save(user);

        return user.getId();
    }
}

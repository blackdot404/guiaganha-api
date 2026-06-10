package com.blackworld.guiaganha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.repositories.UserRepository;
import com.blackworld.guiaganha.services.exceptions.EmailAlreadyExistsException;
import com.blackworld.guiaganha.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Metodo para buscar o email para validar login
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(email));
    }

    // Metodo para cadastrar o usuario no banco(adicionado BCrypt para senha)
    public User registerUser(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return repository.save(user);
    }

}

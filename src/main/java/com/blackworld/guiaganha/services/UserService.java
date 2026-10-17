package com.blackworld.guiaganha.services;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.repositories.UserRepository;
import com.blackworld.guiaganha.services.exceptions.EmailAlreadyExistsException;
import com.blackworld.guiaganha.services.exceptions.IllegalNewLimitException;
import com.blackworld.guiaganha.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repository) {
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

    // Metodo para carregar o perfil do usuario
    public User getUserById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Metodo para atualizar a meta de ganhos diarios
    public User updateDailyRevenueLimit(UUID id, BigDecimal newLimit) {
        User user = getUserById(id);

        if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalNewLimitException(newLimit);
        }

        user.setDailyRevenueLimit(newLimit);
        return repository.save(user);
    }

    // Metodo para atualizar senha
    public void updatePassword(UUID id, String newPassword) {
        User user = getUserById(id);

        String encryptedPassowrd = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassowrd);

        repository.save(user);
    }

    // Metodo para deletar usuario
    public void deleteAccount(UUID id) {
        User user = getUserById(id);

        repository.delete(user);
    }

}

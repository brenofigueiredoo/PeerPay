package br.com.breno.learningspring.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.breno.learningspring.dto.CreateDepositDto;
import br.com.breno.learningspring.dto.UserDto;
import br.com.breno.learningspring.exception.AppException;
import br.com.breno.learningspring.model.User;
import br.com.breno.learningspring.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailAndCpf(final UserDto userData) {
        if (userRepository.existsUserByCpf(userData.getCpf())) {
            throw new AppException("Cpf already in use", HttpStatus.CONFLICT);
        }

        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("Email already in use", HttpStatus.CONFLICT);
        }
    }

    public User createUser(final UserDto userData) {

        checkEmailAndCpf(userData);

        final User user = new User(
                userData.getName(),
                userData.getCpf(),
                userData.getEmail(),
                userData.getPassword(),
                userData.getType());

        return userRepository.save(user);
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User retrieveUser(final long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));

        return user;
    }

    public User updateUser(final UserDto userData, final long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));

        checkEmailAndCpf(userData);

        user.setName(userData.getName());
        user.setCpf(userData.getCpf());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setType(userData.getType());

        return userRepository.save(user);
    }

    public void deleteUser(final long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));

        userRepository.delete(user);
    }

    public User createDeposit(final CreateDepositDto depositData, final long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));

        final float currentBalance = user.getBalance();

        user.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(user);
    }
}

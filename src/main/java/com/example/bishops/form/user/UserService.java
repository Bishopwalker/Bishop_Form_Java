package com.example.bishops.form.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException(
                    "User with email " + user.getEmail() + " already exists"
            );
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalArgumentException(
                    "User with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String firstName, String lastName, String email, String hobbies, Integer age) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with id " + userId + " does not exist"
                ));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }
        if (hobbies != null && hobbies.length() > 0 && !Objects.equals(user.getHobbies(), hobbies)) {
            user.setHobbies(hobbies);
        }
        if (age != null && !Objects.equals(user.getAge(), age)) {
            user.setAge(age);
        }
        userRepository.save(user);
    }
}

package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.UserDto;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void addUser(UserDto userDto) {
        var user = User.builder()
                .telegramId(userDto.getTelegramId())
                .username(userDto.getUsername())
                .build();
        repository.save(user);
        log.info("User added: {}", user.getUsername());
    }

    public UserDto findByUsername(String username) {
        if (!repository.existsByUsername(username)) {
            return null;
        }

        var user = repository.findByUsername(username);

        return UserDto.builder()
                .id(user.getId())
                .telegramId(user.getTelegramId())
                .username(user.getUsername())
                .build();
    }

    public UserDto findByTelegramId(String telegramId) {
        if (!repository.existsByTelegramId(telegramId)) {
            return null;
        }

        var user = repository.findByTelegramId(telegramId);

        return UserDto.builder()
                .id(user.getId())
                .telegramId(user.getTelegramId())
                .username(user.getUsername())
                .build();
    }

    public UserDto findById(Long id) {
        if (!repository.existsById(id)) {
            return null;
        }

        var user = repository.findById(id).get();

        return UserDto.builder()
                .id(user.getId())
                .telegramId(user.getTelegramId())
                .username(user.getUsername())
                .build();
    }
}

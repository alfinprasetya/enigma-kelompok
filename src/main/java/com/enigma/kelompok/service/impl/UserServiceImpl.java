package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.UserRepository;
import com.enigma.kelompok.service.UserService;
import com.enigma.kelompok.utils.SearchUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll(SearchUserRequest request) {
        return null;
    }

    @Override
    public User getOne(Integer id) {
        return null;
    }

    @Override
    public User create(User request) {
        return null;
    }

    @Override
    public User update(Integer id, User request) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

}

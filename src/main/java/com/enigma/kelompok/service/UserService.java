package com.enigma.kelompok.service;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.utils.SearchUserRequest;

import java.util.List;

public interface UserService {

    List<User> getAll(SearchUserRequest request);
    User getOne(Integer id);
    User create(User request);
    User update(Integer id, User request);
    void delete(Integer id);

}
